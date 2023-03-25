package com.project.flinhtinh.model;

import java.io.Serializable;
import java.util.UUID;

public class Store implements Serializable {
    private String storeId;
    private String name;
    private String phone;
    private String email;
    private String city;
    private String address;
    private String status;

    public Store(String storeId, String name, String phone, String email, String city, String address, String status) {
        this.storeId = storeId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.address = address;
        this.status = status;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
