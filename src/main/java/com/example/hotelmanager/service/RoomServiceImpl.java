package com.example.hotelmanager.service;

import com.example.hotelmanager.connection.ConnectionProvider;
import com.example.hotelmanager.dao.RoomDao;
import com.example.hotelmanager.dao.RoomDaoImpl;
import com.example.hotelmanager.model.Room;

import java.util.List;

public class RoomServiceImpl implements RoomService{

    private final RoomDao roomDao;

    public RoomServiceImpl() {
        this.roomDao = new RoomDaoImpl();
    }

    @Override
    public void addRoom(Room room) {
        this.roomDao.addRoom(room);
    }

    @Override
    public void updateRoom(Room room) {
        this.roomDao.updateRoom(room);
    }

    @Override
    public void deleteRoom(int id) {
        this.roomDao.deleteRoom(id);
    }

    @Override
    public Room getRoomById(int id) {
        return this.roomDao.getRoomById(id);
    }

    @Override
    public List<Room> getRooms() {
        return this.roomDao.getRooms();
    }
}
