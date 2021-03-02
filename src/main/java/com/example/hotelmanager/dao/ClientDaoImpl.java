package com.example.hotelmanager.dao;

import com.example.hotelmanager.connection.ConnectionProvider;
import com.example.hotelmanager.connection.ConnectionProviderImpl;
import com.example.hotelmanager.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClientDaoImpl implements ClientDao{

    private static final Logger logger = LogManager.getLogger(ClientDaoImpl.class.getName());

    private final ConnectionProvider conProvider;

    public ClientDaoImpl(){
        this.conProvider = new ConnectionProviderImpl();
    }

    @Override
    public void addClient(Client client) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "INSERT INTO clients VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setString(1, client.getNumPass());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public void updateClient(Client client) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "UPDATE clients SET first_name=?, last_name=? WHERE num_pass=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getNumPass());
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public void deleteClient(String id) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "DELETE FROM clients WHERE num_pass=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public Client getClientById(String id) {
        Client client = new Client();
        try(Connection connection = conProvider.getConnection()){
            String stat = "SELECT* FROM clients WHERE num_pass=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setString(1,id);
            try(ResultSet res = ps.executeQuery()){
                if(res.next()){
                    client.setNumPass(res.getString(1));
                    client.setFirstName(res.getString(2));
                    client.setLastName(res.getString(3));
                }
            }
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
        return client;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clientList = new LinkedList<>();
        try(Connection connection = conProvider.getConnection()){
            Statement st = connection.createStatement();
            try(ResultSet res = st.executeQuery("SELECT* FROM clients")){
                while (res.next()){
                    Client client = new Client();
                    client.setNumPass(res.getString(1));
                    client.setFirstName(res.getString(2));
                    client.setLastName(res.getString(3));
                    clientList.add(client);
                }
            }
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
        return clientList;
    }
}
