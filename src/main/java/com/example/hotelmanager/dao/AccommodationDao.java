package com.example.hotelmanager.dao;

import com.example.hotelmanager.model.Accommodation;
import com.example.hotelmanager.model.Category;

import java.util.List;

public interface AccommodationDao {

    void addAccommodation(Accommodation accommodation);
    void updateAccommodation(Accommodation accommodation);
    void deleteAccommodation(int id);
    Accommodation getAccommodationById(int id);
    List<Accommodation> getAccommodations();

}
