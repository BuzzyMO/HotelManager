package com.example.hotelmanager.model;

import java.sql.Date;

public class Accommodation {

    private int accommodationId;
    private String numPass;
    private Date checkIn;
    private Date checkOut;
    private int number;

    public int getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(int accommodationId) {
        this.accommodationId = accommodationId;
    }

    public String getNumPass() {
        return numPass;
    }

    public void setNumPass(String numPass) {
        this.numPass = numPass;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "accommodationId=" + accommodationId +
                ", numPass='" + numPass + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", number=" + number +
                '}';
    }
}
