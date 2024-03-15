package com.product.solution.dto;

import javax.validation.constraints.Min;

public class UpdateProductRequest {
    @Min(value = 1, message = "product price can not be zero or negative")
    private int price;
    @Min(value = 1, message = "product stock can not be zero or negative")
    private int stock;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
