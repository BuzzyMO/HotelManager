package com.example.hotelmanager.servlet;

import com.example.hotelmanager.model.Room;
import com.example.hotelmanager.service.RoomService;
import com.example.hotelmanager.service.RoomServiceImpl;
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

@WebServlet(name = "RoomServlet", urlPatterns = "/api/room")
public class RoomServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(RoomServlet.class.getName());

    private final Gson gson = new Gson();
    private final RoomService roomService = new RoomServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String stringId = request.getParameter("id");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try(PrintWriter out = response.getWriter()){
            if(stringId != null){
                int id = Integer.parseInt(stringId);
                Room room = roomService.getRoomById(id);
                String roomJson = gson.toJson(room);
                out.print(roomJson);
            } else {
                List<Room> roomList = roomService.getRooms();
                String roomListJson = gson.toJson(roomList);
                out.print(roomListJson);
            }
            out.flush();
        } catch (IOException ex){
            logger.fatal(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try(BufferedReader reader = request.getReader()){
            Room room = gson.fromJson(reader, Room.class);
            roomService.addRoom(room);
        } catch (IOException ex){
            logger.fatal("Can't read POST request msg", ex);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        try(BufferedReader reader = request.getReader()){
            Room room = gson.fromJson(reader, Room.class);
            roomService.updateRoom(room);
        } catch (IOException ex){
            logger.fatal("Can't read PUT request msg", ex);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        String stringId = request.getParameter("id");
        if(stringId != null){
            int id = Integer.parseInt(stringId);
            roomService.deleteRoom(id);
        }
    }
}
