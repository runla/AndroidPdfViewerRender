package com.github.barteksc.pdfviewer;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;

import com.github.barteksc.pdfviewer.model.Size;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PdfiumCore {
    private static final String TAG = PdfiumCore.class.getName();
    private static final Class FD_CLASS = FileDescriptor.class;
    private static final String FD_FIELD_NAME = "descriptor";
    private static final Object lock;
    private static Field mFdField;
    private int mCurrentDpi;

    private native void nativeCloseDocument(long var1);


    public PdfiumCore(Context ctx) {
        this.mCurrentDpi = ctx.getResources().getDisplayMetrics().densityDpi;
        Log.d(TAG, "Starting PdfiumAndroid 1.9.0");
    }

    public PdfDocument newDocument(ParcelFileDescriptor fd) throws IOException {
        return this.newDocument((ParcelFileDescriptor)fd, (String)null);
    }

    @SuppressLint("NewApi")
    public PdfDocument newDocument(ParcelFileDescriptor fd, String password) throws IOException {
        PdfDocument document = new PdfDocument();
        document.parcelFileDescriptor = fd;
        document.pdfRenderer = new PdfRenderer(fd);
        return document;
    }


    @SuppressLint("NewApi")
    public int getPageCount(PdfDocument doc) {
        synchronized(lock) {
            return doc.pdfRenderer.getPageCount();
        }
    }

    @SuppressLint("NewApi")
    public long openPage(PdfDocument doc, int pageIndex) {
        synchronized(lock) {
            Log.d(TAG, "openPage: " + pageIndex);
            PdfRenderer.Page page  = doc.pdfRenderer.openPage(pageIndex);
            return 0;
        }
    }



    @SuppressLint("NewApi")
    public Size getPageSize(PdfDocument doc, int index) {
        synchronized(lock) {
            PdfRenderer.Page page = doc.pdfRenderer.openPage(index);
            Size size = new Size(page.getWidth(), page.getHeight());
            page.close();
            return size;
        }
    }


    @SuppressLint("NewApi")
    public void renderPageBitmap(PdfDocument doc, Bitmap bitmap, int pageIndex, int startX, int startY, int drawSizeX, int drawSizeY, boolean renderAnnot) {
        synchronized(lock) {
            PdfRenderer.Page page = null;
            try {
                bitmap.getHeight();

                Log.e(TAG, "renderPageBitmap: 1pageIndex:"+pageIndex);
                page = doc.pdfRenderer.openPage(pageIndex);
                Log.e(TAG, "renderPageBitmap: 2pageIndex:"+pageIndex);
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            } catch (NullPointerException var12) {
                Log.e(TAG, "mContext may be null");
                var12.printStackTrace();
            } catch (Exception var13) {
                Log.e(TAG, "Exception throw from native");
                var13.printStackTrace();
            } finally {
                if (page != null) {
                    page.close();
                }
            }

        }
    }

    @SuppressLint("NewApi")
    public void closeDocument(PdfDocument doc) {
        synchronized(lock) {

            if (doc.parcelFileDescriptor != null) {
                try {
                    doc.parcelFileDescriptor.close();
                } catch (IOException var6) {
                }
                doc.parcelFileDescriptor = null;
            }
            if (doc.pdfRenderer != null) {
                doc.pdfRenderer.close();
                doc.pdfRenderer = null;
            }
        }
    }

    static {
        lock = new Object();
        mFdField = null;
    }
}
