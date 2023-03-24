package com.project.flinhtinh.model;

import java.util.UUID;

public class Staff {
    private UUID staffId;
    private String fullName;
    private String phone;
    private String email;
    private Store store;
    private boolean manager;
    private String status;

    public Staff(UUID staffId, String fullName, String phone, String email, Store store, boolean manager, String status) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.store = store;
        this.manager = manager;
        this.status = status;
    }

    public UUID getStaffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
