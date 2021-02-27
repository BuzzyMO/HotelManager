package com.example.hotelmanager.service;

import com.example.hotelmanager.model.Accommodation;

import java.util.List;

public interface AccommodationService {
    void addAccommodation(Accommodation accommodation);
    void updateAccommodation(Accommodation accommodation);
    void deleteAccommodation(int id);
    Accommodation getAccommodationById(int id);
    List<Accommodation> getAccommodations();
}
