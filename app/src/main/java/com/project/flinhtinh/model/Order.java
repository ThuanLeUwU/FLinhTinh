package com.project.flinhtinh.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Order implements Serializable {
    private String orderId;
    private Customer customer;
    private Store store;
    private Staff staff;
    private double totalPrice;
    private Date orderDate;
    private String city;
    private String address;
    private String status;

    public Order(String orderId, Customer customer, Store store, Staff staff, double totalPrice, Date orderDate, String city, String address, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.store = store;
        this.staff = staff;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.city = city;
        this.address = address;
        this.status = status;
    }

    public Order() {

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
