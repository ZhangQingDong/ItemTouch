package com.example.zqd.itemtouch;

public class ItemBean {

    private String name;

    private boolean status;

    public ItemBean(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }
}
