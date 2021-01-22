package com.example.commsci_android1.ui.notifications;

public class DataIntercom {
    private String head;
    private String desc;
    private String image;
    private String id;

    public DataIntercom(String head, String desc, String image, String id) {
        this.head = head;
        this.desc = desc;
        this.image = image;
        this.id = id;
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

    public String getId() { return id; }
}
