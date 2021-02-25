package com.example.hotelmanager.dao;

import com.example.hotelmanager.connection.ConnectionProvider;
import com.example.hotelmanager.model.Accommodation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AccommodationDaoImpl implements AccommodationDao{

    private static final Logger logger = LogManager.getLogger(AccommodationDaoImpl.class.getName());

    private final ConnectionProvider conProvider;

    public AccommodationDaoImpl(ConnectionProvider conProvider){
        this.conProvider = conProvider;
    }

    @Override
    public void addAccommodation(Accommodation accommodation) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "INSERT INTO accommodations VALUES(DEFAULT,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(stat);
            //ps.setInt(1,accommodation.getAccommodationId());
            ps.setString(1,accommodation.getNumPass());
            ps.setDate(2,accommodation.getCheckIn());
            ps.setDate(3,accommodation.getCheckOut());
            ps.setInt(4,accommodation.getNumber());
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public void updateAccommodation(Accommodation accommodation) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "UPDATE accommodations SET num_pass=?, check_in=?, check_out=?, number=? WHERE accommodation_id=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setString(1,accommodation.getNumPass());
            ps.setDate(2,accommodation.getCheckIn());
            ps.setDate(3,accommodation.getCheckOut());
            ps.setInt(4,accommodation.getNumber());
            ps.setInt(5,accommodation.getAccommodationId());
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public void deleteAccommodation(int id) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "DELETE FROM accommodations WHERE accommodation_id=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public Accommodation getAccommodationById(int id) {
        Accommodation accommodation = new Accommodation();
        try(Connection connection = conProvider.getConnection()){
            String stat = "SELECT* FROM accommodations WHERE accommodation_id=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setInt(1,id);
            try(ResultSet res = ps.executeQuery()){
                if(res.next()){
                    accommodation.setAccommodationId(res.getInt(1));
                    accommodation.setNumPass(res.getString(2));
                    accommodation.setCheckIn(res.getDate(3));
                    accommodation.setCheckOut(res.getDate(4));
                    accommodation.setNumber(res.getInt(5));
                }
            }
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
        return accommodation;
    }

    @Override
    public List<Accommodation> getAccommodations() {
        List<Accommodation> accommodationList = new LinkedList<>();
        try(Connection connection = conProvider.getConnection()){
            Statement st = connection.createStatement();
            try(ResultSet res = st.executeQuery("SELECT* FROM accommodations")){
                while (res.next()){
                    Accommodation accommodation = new Accommodation();
                    accommodation.setAccommodationId(res.getInt(1));
                    accommodation.setNumPass(res.getString(2));
                    accommodation.setCheckIn(res.getDate(3));
                    accommodation.setCheckOut(res.getDate(4));
                    accommodation.setNumber(res.getInt(5));
                    accommodationList.add(accommodation);
                }
            }
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
        return accommodationList;
    }
}
