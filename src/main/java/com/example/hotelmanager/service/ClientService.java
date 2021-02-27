package com.example.hotelmanager.service;

import com.example.hotelmanager.model.Client;

import java.util.List;

public interface ClientService {
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(String id);
    Client getClientById(String id);
    List<Client> getClients();
}
