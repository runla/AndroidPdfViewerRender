package com.github.barteksc.pdfviewer.model;

/**
 * @description：
 * @author： chenjianrun
 * @date： 2023/9/1 11:35 上午
 */
public class SizeF  {
    public float width;
    public float height;

    public SizeF(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
