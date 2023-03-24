package com.project.flinhtinh.model;

import java.util.UUID;

public class Product {
    private UUID productId;
    private String name;
    private Brand brand;
    private Category category;
    private int quantity;
    private double price;
    private int modelYear;
    private String image;
    private String description;
    private String status;

    public Product(UUID productId, String name, Brand brand, Category category, int quantity, double price, int modelYear, String image, String description, String status) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.modelYear = modelYear;
        this.image = image;
        this.description = description;
        this.status = status;
    }

    public Product() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
