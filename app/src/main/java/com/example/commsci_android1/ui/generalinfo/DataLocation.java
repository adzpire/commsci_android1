package com.example.commsci_android1.ui.generalinfo;

public class DataLocation {
    private String head;
    private String desc;
    private String image;

    public DataLocation(String head, String desc, String image) {
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
