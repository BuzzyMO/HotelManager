package com.example.hotelmanager.model;

import java.math.BigDecimal;

public class Category {

    private int categoryId;
    private String roomCategory;
    private String description;
    private BigDecimal price;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", roomCategory='" + roomCategory + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
