package com.example.casestudy3.DAO;

import com.example.casestudy3.connection.MyConnection;
import com.example.casestudy3.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private Connection connection;
    private final String SELECT_USER_BY_ID = "select * from users where id = ?;";
    private final String INSERT_USER = "insert into " +
            "users(userAccount, userPassword, fullName, telephone, email, waller, status) value (?,?,?,?,?,0,1);";
    private final String UPDATE_USER = "update users set fullName = ?, telephone = ?, email = ?;";
    private final String DELETE_USER = "delete from users where id = ?;";

    public UserDAO() {
        connection = MyConnection.getConnection();
    }

    public User findUserById(Long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String userAccount = resultSet.getString("userAccount");
                String password = resultSet.getString("password");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                double wallet = resultSet.getDouble("wallet");
                int status = resultSet.getInt("status");
                return new User(id, fullName, userAccount, password, telephone, email, wallet, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createUser(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getEmail());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

}
