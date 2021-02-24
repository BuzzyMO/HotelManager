package com.example.hotelmanager.dao;

import com.example.hotelmanager.model.Client;

import java.util.List;

public interface ClientDao {

    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(String id);
    Client getClientById(String id);
    List<Client> getClients();

}
