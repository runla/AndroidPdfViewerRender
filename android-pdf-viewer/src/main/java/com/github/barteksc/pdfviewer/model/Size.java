package com.github.barteksc.pdfviewer.model;

/**
 * @description：
 * @author： chenjianrun
 * @date： 2023/9/1 11:36 上午
 */
public class Size {
    public int width;
    public int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

