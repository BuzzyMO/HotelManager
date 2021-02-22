package com.example.hotelmanager.model;

public class Room {

    private int number;
    private int floor;
    private int categoryId;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", floor=" + floor +
                ", categoryId=" + categoryId +
                '}';
    }
}
