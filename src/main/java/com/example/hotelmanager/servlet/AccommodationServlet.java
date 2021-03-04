package com.example.hotelmanager.servlet;

import com.example.hotelmanager.model.Accommodation;
import com.example.hotelmanager.service.AccommodationService;
import com.example.hotelmanager.service.AccommodationServiceImpl;
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

@WebServlet(name = "AccommodationServlet", urlPatterns = "/api/accommodation")
public class AccommodationServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(AccommodationServlet.class.getName());

    private final Gson gson = new Gson();
    private final AccommodationService accService = new AccommodationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String stringId = request.getParameter("id");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try(PrintWriter out = response.getWriter()){
            if(stringId != null){
                int id = Integer.parseInt(stringId);
                Accommodation acc = accService.getAccommodationById(id);
                String accJson = gson.toJson(acc);
                out.print(accJson);
            } else {
                List<Accommodation> accList = accService.getAccommodations();
                String accListJson = gson.toJson(accList);
                out.print(accListJson);
            }
            out.flush();
        } catch (IOException ex){
            logger.fatal(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try(BufferedReader reader = request.getReader()){
            Accommodation acc = gson.fromJson(reader, Accommodation.class);
            accService.addAccommodation(acc);
        } catch (IOException ex){
            logger.fatal("Can't read POST request msg", ex);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        try(BufferedReader reader = request.getReader()){
            Accommodation acc = gson.fromJson(reader, Accommodation.class);
            accService.updateAccommodation(acc);
        } catch (IOException ex){
            logger.fatal("Can't read PUT request msg", ex);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        String stringId = request.getParameter("id");
        if(stringId != null){
            int id = Integer.parseInt(stringId);
            accService.deleteAccommodation(id);
        }
    }
}
