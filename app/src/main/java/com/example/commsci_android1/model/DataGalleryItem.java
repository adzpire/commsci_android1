package com.example.commsci_android1.model;

public class DataGalleryItem {
    private String head;
    private String desc;
    private String image;

    public DataGalleryItem(String head, String desc, String image) {
        this.head = head;
        this.desc = desc;
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public String getHead() {
        return head;
    }

    public String getImage() {
        return image;
    }
}
