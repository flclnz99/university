package com.example.shopservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {

    @Id
    private String id;
    private String itemName;
    private String artistName;
    private String vendor;
    private String description;
    private String imageURL;
    private String location;
    private String date;
    private ItemType type;
    private String productType;
    private double price;
    private int availability;


    public Item() {
    }

    public Item(String itemName, String artistName, String vendor, String description, String imageURL, ItemType type, String productType, double price, int availability) {
        this.itemName = itemName;
        this.artistName = artistName;
        this.vendor = vendor;
        this.description = description;
        this.imageURL = imageURL;
        this.type = type;
        this.productType = productType;
        this.price = price;
        this.availability = availability;
    }


    public Item(String itemName, String artistName, String vendor, String description, String imageURL, String location, String date, ItemType type, double price, int availability) {
        this.itemName = itemName;
        this.artistName = artistName;
        this.vendor = vendor;
        this.description = description;
        this.imageURL = imageURL;
        this.location = location;
        this.date = date;
        this.type = type;
        this.price = price;
        this.availability = availability;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {this.itemName = itemName;}

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", ItemName='" + itemName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", vendor='" + vendor + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", availability=" + availability +
                '}';
    }
}

