package com.example.hotelmanager.dao;

import com.example.hotelmanager.model.Room;

import java.util.List;

public interface RoomDao {

    void addRoom(Room room);
    void updateRoom(Room room);
    void deleteRoom(int id);
    Room getRoomById(int id);
    List<Room> getRooms();

}
