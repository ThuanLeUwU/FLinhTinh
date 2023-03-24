package com.project.flinhtinh.model;

import java.util.UUID;

public class Brand {
    private UUID brandId;
    private String brandName;
    private String status;

    public Brand(UUID brandId, String brandName, String status) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.status = status;
    }

    public UUID getBrandId() {
        return brandId;
    }

    public void setBrandId(UUID brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
