package com.example.recyclerview_whatsappdesign;

public class ModelClass {
    private int ivImage;
    private String tvName, tvTime, tvMessage, tvDivider;

    ModelClass(int ivImage, String tvName, String tvTime, String tvMessage, String tvDivider) {
        this.ivImage = ivImage;
        this.tvName = tvName;
        this.tvTime = tvTime;
        this.tvMessage = tvMessage;
        this.tvDivider = tvDivider;
    }

    public int getIvImage() {
        return ivImage;
    }

    public String getTvName() {
        return tvName;
    }

    public String getTvTime() {
        return tvTime;
    }

    public String getTvMessage() {
        return tvMessage;
    }

    public String getTvDivider() {
        return tvDivider;
    }
}
