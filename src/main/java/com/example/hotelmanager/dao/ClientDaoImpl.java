package com.example.hotelmanager.dao;

import com.example.hotelmanager.connection.ConnectionProvider;
import com.example.hotelmanager.connection.ConnectionProviderImpl;
import com.example.hotelmanager.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ClientDaoImpl implements ClientDao{

    private static Logger logger = LogManager.getLogger(ClientDaoImpl.class.getName());

    private ConnectionProvider conProvider;

    public ClientDaoImpl(ConnectionProvider conProvider){
        this.conProvider = conProvider;
    }

    @Override
    public void addClient(Client client) {
        try(Connection connection = conProvider.getConnection()){
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO clients(num_pass, first_name, last_name) VALUES(" +
                    client.getNumPass() + ", " +
                    client.getFirstName() + ", " +
                    client.getLastName() + ");");
        } catch (SQLException ex){
            logger.debug(ex.getMessage(),ex);
        }
    }

    @Override
    public void updateClient(Client client) {

    }

    @Override
    public void deleteClient(String id) {
        try(Connection connection = conProvider.getConnection()){
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM clients WHERE num_pass = " + id +";");
        } catch (SQLException ex){
            logger.debug(ex.getMessage(),ex);
        }
    }

    @Override
    public Client getClientById(String id) {
        Client client = new Client(); // can return with null fields
        try(Connection connection = conProvider.getConnection()){
            Statement st = connection.createStatement();
            try(ResultSet res = st.executeQuery("SELECT* FROM clients WHERE num_pass = " + id +";")){
                if(res.next()){
                    client.setNumPass(res.getString(1));
                    client.setFirstName(res.getString(2));
                    client.setLastName(res.getString(3));
                }
            }

        } catch (SQLException ex){
            logger.debug(ex.getMessage(),ex);
        }
        return client;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clientList = new LinkedList<>();
        try(Connection connection = conProvider.getConnection()){
            Statement st = connection.createStatement();
            try(ResultSet res = st.executeQuery("SELECT* FROM clients;")){
                if(res.next()){
                    Client client = new Client();
                    client.setNumPass(res.getString(1));
                    client.setFirstName(res.getString(2));
                    client.setLastName(res.getString(3));
                    clientList.add(client);
                }
            }

        } catch (SQLException ex){
            logger.debug(ex.getMessage(),ex);
        }
        return clientList;
    }
}
