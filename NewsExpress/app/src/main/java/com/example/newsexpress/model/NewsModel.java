package com.example.newsexpress.model;

public class NewsModel {

    String imageSrc;
    String newsHeadline;

    public NewsModel(String imageSrc, String newsHeadline) {
        this.imageSrc = imageSrc;
        this.newsHeadline = newsHeadline;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline = newsHeadline;
    }
}
