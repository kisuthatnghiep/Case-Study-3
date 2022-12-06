package com.example.casestudy3.DAO;

import com.example.casestudy3.connection.MyConnection;
import com.example.casestudy3.model.Singer;
import com.example.casestudy3.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private Connection connection;
    private final String SELECT_ALL_USERS = "select * from users where status = 1;";
    private final String SELECT_ALL_SINGERS = "select * from singer where status = 2;";

    public AdminDAO() {
        connection = MyConnection.getConnection();
    }
    public List<User> findAllUser() {
        List<User> users = new ArrayList<User>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("fullName");
                String account = resultSet.getString("userAccount");
                String password = resultSet.getString("userPassword");
                String phone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                double wallet = resultSet.getDouble("wallet");
                int status = resultSet.getInt("status");
                users.add(new User(id, name,account,password,phone,email,wallet,status ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public List<Singer> findAllSinger() {
        List<Singer> singers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SINGERS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("fullName");
                String account = resultSet.getString("userAccount");
                String password = resultSet.getString("userPassword");
                String phone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                double income = resultSet.getDouble("income");
                int status = resultSet.getInt("status");
                singers.add(new Singer(id, name,account,password,phone,email,income,status ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singers;
    }
    public User findByIdUser(long id) {
        for (User u : findAllUser()){
            if (u.getId() == id){
                return u;
            }
        }
        return null;
    }
    public Singer findByIdSinger(long id) {
        for (Singer s : findAllSinger()){
            if (s.getId() == id){
                return s;
            }
        }
        return null;
    }
}
