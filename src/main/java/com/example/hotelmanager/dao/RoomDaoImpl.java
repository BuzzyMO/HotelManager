package com.example.hotelmanager.dao;

import com.example.hotelmanager.connection.ConnectionProvider;
import com.example.hotelmanager.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RoomDaoImpl implements RoomDao{

    private static final Logger logger = LogManager.getLogger(RoomDaoImpl.class.getName());

    private final ConnectionProvider conProvider;

    public RoomDaoImpl(ConnectionProvider conProvider){
        this.conProvider = conProvider;
    }

    @Override
    public void addRoom(Room room) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "INSERT INTO rooms VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setInt(1, room.getNumber());
            ps.setInt(2, room.getFloor());
            ps.setInt(3, room.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public void updateRoom(Room room) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "UPDATE rooms SET floor=?, category_id=? WHERE number=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setInt(1, room.getFloor());
            ps.setInt(2, room.getCategoryId());
            ps.setInt(3, room.getNumber());
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public void deleteRoom(int id) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "DELETE FROM rooms WHERE number=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public Room getRoomById(int id) {
        Room room = new Room();
        try(Connection connection = conProvider.getConnection()){
            String stat = "SELECT* FROM rooms WHERE number=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setInt(1,id);
            try(ResultSet res = ps.executeQuery()){
                if(res.next()){
                    room.setNumber(res.getInt(1));
                    room.setFloor(res.getInt(2));
                    room.setCategoryId(res.getInt(3));
                }
            }
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
        return room;
    }

    @Override
    public List<Room> getRooms() {
        List<Room> roomList = new LinkedList<>();
        try(Connection connection = conProvider.getConnection()){
            Statement st = connection.createStatement();
            try(ResultSet res = st.executeQuery("SELECT* FROM rooms")){
                while (res.next()){
                    Room room = new Room();
                    room.setNumber(res.getInt(1));
                    room.setFloor(res.getInt(2));
                    room.setCategoryId(res.getInt(3));
                    roomList.add(room);
                }
            }
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
        return roomList;
    }
}
