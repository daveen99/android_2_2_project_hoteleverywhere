package com.example.hotel_everywhere;

public class ListItem {
    private String itemName;
    private int imageResourceId;

    public ListItem(String itemName, int imageResourceId) {
        this.itemName = itemName;
        this.imageResourceId = imageResourceId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}