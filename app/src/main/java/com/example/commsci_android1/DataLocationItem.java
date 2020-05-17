package com.example.commsci_android1;

public class DataLocationItem {
    private String head;
    private String desc;
    private String image;

    public DataLocationItem(String head, String desc, String image) {
        this.head = head;
        this.desc = desc;
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
