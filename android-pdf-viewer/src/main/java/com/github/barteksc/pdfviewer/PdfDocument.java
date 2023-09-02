package com.github.barteksc.pdfviewer;

import android.graphics.RectF;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description：
 * @author： chenjianrun
 * @date： 2023/9/1 11:42 上午
 */

public class PdfDocument {
    ParcelFileDescriptor parcelFileDescriptor;
    PdfRenderer pdfRenderer;


    PdfDocument() {
    }

    public static class Bookmark {
        private List<Bookmark> children = new ArrayList();
        String title;
        long pageIdx;
        long mNativePtr;

        public Bookmark() {
        }

        public List<Bookmark> getChildren() {
            return this.children;
        }

        public boolean hasChildren() {
            return !this.children.isEmpty();
        }

        public String getTitle() {
            return this.title;
        }

        public long getPageIdx() {
            return this.pageIdx;
        }
    }

}

