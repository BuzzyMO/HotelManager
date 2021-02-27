package com.example.hotelmanager.service;

import com.example.hotelmanager.dao.ClientDao;
import com.example.hotelmanager.model.Client;

import java.util.List;

public class ClientServiceImpl implements ClientService{

    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void addClient(Client client) {
        this.clientDao.addClient(client);
    }

    @Override
    public void updateClient(Client client) {
        this.clientDao.updateClient(client);
    }

    @Override
    public void deleteClient(String id) {
        this.clientDao.deleteClient(id);
    }

    @Override
    public Client getClientById(String id) {
        return this.clientDao.getClientById(id);
    }

    @Override
    public List<Client> getClients() {
        return this.clientDao.getClients();
    }
}
