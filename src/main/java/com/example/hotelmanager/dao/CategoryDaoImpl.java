package com.example.hotelmanager.dao;

import com.example.hotelmanager.connection.ConnectionProvider;
import com.example.hotelmanager.connection.ConnectionProviderImpl;
import com.example.hotelmanager.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao{

    private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class.getName());

    private final ConnectionProvider conProvider;

    public CategoryDaoImpl(){
        this.conProvider = new ConnectionProviderImpl();
    }

    @Override
    public void addCategory(Category category) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "INSERT INTO categories VALUES(DEFAULT,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setString(1, category.getRoomCategory());
            ps.setString(2, category.getDescription());
            ps.setBigDecimal(3, category.getPrice());
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public void updateCategory(Category category) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "UPDATE categories SET room_category=?, description=?, price=? WHERE category_id=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setString(1, category.getRoomCategory());
            ps.setString(2, category.getDescription());
            ps.setBigDecimal(3, category.getPrice());
            ps.setInt(4,category.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public void deleteCategory(int id) {
        try(Connection connection = conProvider.getConnection()){
            String stat = "DELETE FROM categories WHERE category_id=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
    }

    @Override
    public Category getCategoryById(int id) {
        Category category = new Category();
        try(Connection connection = conProvider.getConnection()){
            String stat = "SELECT* FROM categories WHERE category_id=?";
            PreparedStatement ps = connection.prepareStatement(stat);
            ps.setInt(1,id);
            try(ResultSet res = ps.executeQuery()){
                if(res.next()){
                    category.setCategoryId(res.getInt(1));
                    category.setRoomCategory(res.getString(2));
                    category.setDescription(res.getString(3));
                    category.setPrice(res.getBigDecimal(4));
                }
            }
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
        return category;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categoryList = new LinkedList<>();
        try(Connection connection = conProvider.getConnection()){
            Statement st = connection.createStatement();
            try(ResultSet res = st.executeQuery("SELECT* FROM categories")){
                while (res.next()){
                    Category category = new Category();
                    category.setCategoryId(res.getInt(1));
                    category.setRoomCategory(res.getString(2));
                    category.setDescription(res.getString(3));
                    category.setPrice(res.getBigDecimal(4));
                    categoryList.add(category);
                }
            }
        } catch (SQLException ex){
            logger.fatal(ex.getMessage(),ex);
        }
        return categoryList;
    }
}
