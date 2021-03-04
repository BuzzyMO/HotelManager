package com.example.hotelmanager.servlet;

import com.example.hotelmanager.model.Client;
import com.example.hotelmanager.service.ClientService;
import com.example.hotelmanager.service.ClientServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ClientServlet", urlPatterns = "/api/client")
public class ClientServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(ClientServlet.class.getName());

    private final Gson gson = new Gson();
    private final ClientService clientService = new ClientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try(PrintWriter out = response.getWriter()){
            if(id != null){
                Client client = clientService.getClientById(id);
                String clientJson = gson.toJson(client);
                out.print(clientJson);
            } else {
                List<Client> clientList = clientService.getClients();
                String clientListJson = gson.toJson(clientList);
                out.print(clientListJson);
            }
            out.flush();
        } catch (IOException ex){
            logger.fatal(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try(BufferedReader reader = request.getReader()){
            Client client = gson.fromJson(reader, Client.class);
            clientService.addClient(client);
        } catch (IOException ex){
            logger.fatal("Can't read POST request msg", ex);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        try(BufferedReader reader = request.getReader()){
            Client client = gson.fromJson(reader, Client.class);
            clientService.updateClient(client);
        } catch (IOException ex){
            logger.fatal("Can't read PUT request msg", ex);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        if(id != null){
            clientService.deleteClient(id);
        }
    }
}
