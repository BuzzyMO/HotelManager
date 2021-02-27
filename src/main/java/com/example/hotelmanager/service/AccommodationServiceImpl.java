package com.example.hotelmanager.service;

import com.example.hotelmanager.dao.AccommodationDao;
import com.example.hotelmanager.model.Accommodation;

import java.util.List;

public class AccommodationServiceImpl implements AccommodationService{

    private final AccommodationDao acDao;

    public AccommodationServiceImpl(AccommodationDao acDao){
        this.acDao = acDao;
    }

    @Override
    public void addAccommodation(Accommodation accommodation) {
        this.acDao.addAccommodation(accommodation);
    }

    @Override
    public void updateAccommodation(Accommodation accommodation) {
        this.acDao.updateAccommodation(accommodation);
    }

    @Override
    public void deleteAccommodation(int id) {
        this.acDao.deleteAccommodation(id);
    }

    @Override
    public Accommodation getAccommodationById(int id) {
        return this.acDao.getAccommodationById(id);
    }

    @Override
    public List<Accommodation> getAccommodations() {
        return this.acDao.getAccommodations();
    }
}
