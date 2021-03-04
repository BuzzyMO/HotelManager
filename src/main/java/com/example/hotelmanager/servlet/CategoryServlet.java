package com.example.hotelmanager.servlet;

import com.example.hotelmanager.model.Category;
import com.example.hotelmanager.service.CategoryService;
import com.example.hotelmanager.service.CategoryServiceImpl;
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

@WebServlet(name = "CategoryServlet", urlPatterns = "/api/category")
public class CategoryServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(CategoryServlet.class.getName());

    private final Gson gson = new Gson();
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String stringId = request.getParameter("id");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try(PrintWriter out = response.getWriter()){
            if(stringId != null){
                int id = Integer.parseInt(stringId);
                Category category = categoryService.getCategoryById(id);
                String categoryJson = gson.toJson(category);
                out.print(categoryJson);
            } else {
                List<Category> categoryList = categoryService.getCategories();
                String categoryListJson = gson.toJson(categoryList);
                out.print(categoryListJson);
            }
            out.flush();
        } catch (IOException ex){
            logger.fatal(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try(BufferedReader reader = request.getReader()){
            Category category = gson.fromJson(reader, Category.class);
            categoryService.addCategory(category);
        } catch (IOException ex){
            logger.fatal("Can't read POST request msg", ex);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        try(BufferedReader reader = request.getReader()){
            Category category = gson.fromJson(reader, Category.class);
            categoryService.updateCategory(category);
        } catch (IOException ex){
            logger.fatal("Can't read PUT request msg", ex);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        String stringId = request.getParameter("id");
        if(stringId != null){
            int id = Integer.parseInt(stringId);
            categoryService.deleteCategory(id);
        }
    }
}
