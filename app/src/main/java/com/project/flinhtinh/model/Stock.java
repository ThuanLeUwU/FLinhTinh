package com.project.flinhtinh.model;

import java.util.List;
import java.util.UUID;

public class Stock {
    private UUID stockId;
    private Store store;
    private List<Product> listProduct;


    public Stock(UUID stockId, Store store, List<Product> listProduct, int quantity) {
        this.stockId = stockId;
        this.store = store;
        this.listProduct = listProduct;
    }

    public UUID getStockId() {
        return stockId;
    }

    public void setStockId(UUID stockId) {
        this.stockId = stockId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

}
