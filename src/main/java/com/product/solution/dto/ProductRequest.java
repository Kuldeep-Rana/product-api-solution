package com.product.solution.dto;

import javax.validation.constraints.*;

public class ProductRequest {
    @NotEmpty(message = "product name can not be empty")
    private String name;
    @NotEmpty(message = "product description can not be empty")
    private String description;
    @NotEmpty(message = "product vendor can not be empty")
    private String vendor;
    @Min(value = 1, message = "product price can not be zero or negative")
    private int price;
    @Min(value = 1, message = "product stock can not be zero or negative")
    private int stock;
    @NotEmpty(message = "product currency can not be empty")
    @Pattern(regexp = "EUR|USD", message = "currency must be either 'EUR' or 'USD'")
    private String currency;
    private String image_url;
    private String sku;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

}
