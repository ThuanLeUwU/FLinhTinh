package com.project.flinhtinh.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cart implements Serializable {
    private String cartId;
    private HashMap<UUID, OrderDetail> cart;


    public Cart() {
    }

    public Cart(String cartId, HashMap<UUID, OrderDetail> cart) {
        this.cartId = cartId;
        this.cart = cart;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public HashMap<UUID, OrderDetail> getCart() {
        return cart;
    }

    public void addItem(UUID productId, OrderDetail orderDetail) {
        cart.put(productId, orderDetail);
    }

    public void removeItem(UUID id) {
            if (cart.containsKey(id)) {
                cart.remove(id);
            }
    }

    public void editItem(UUID productId, int quantity) {
            if (cart.containsKey(productId)) {
                cart.get(productId).setQuantity(quantity);
            }
    }

    public double totalPrice() {
        double total = 0;
        for (Map.Entry<UUID, OrderDetail> entry : cart.entrySet()) {
            total += (entry.getValue().getQuantity() * entry.getValue().getPrice());
        }
        return total;
    }

    public int totalItem() {
        return cart.size();
    }

    public void setCart(HashMap<UUID, OrderDetail> cart) {
        this.cart = cart;
    }
}
