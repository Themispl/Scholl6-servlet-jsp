package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.security.SecUtil;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements IUserDAO{
    @Override
    public User insert(User user) throws UserDAOException {
        String sql = "insert into users (username, password) values (?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps= connection.prepareStatement(sql)){
            //extract model info
            String username = user.getUsername();
            String password = user.getPassword();

            ps.setString(1, username);
            ps.setString(2, SecUtil.hashPassword(password));

            ps.executeUpdate();
            //logging
            return user;

        }catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL error in user insert" + user);
        }
    }

    @Override
    public User getByUsername(String username) throws UserDAOException {
        String sql = "SELECT * FROM users where username = ?";
        User user = null;
        ResultSet rs;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps= connection.prepareStatement(sql)){
            //extract model info

            ps.setString(1, username);
            rs = ps.executeQuery();

            if(rs.next()){
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            return user;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL error in user search" + username);
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;
        ResultSet rs;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps= connection.prepareStatement(sql)){
            //extract model info

            ps.setString(1, username);
            rs = ps.executeQuery();

            if(rs.next()){
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }else{
                return false;
            }
            return SecUtil.checkPassword(password, user.getPassword());
        }catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL error in user valid for username: " + username);
        }
    }

    @Override
    public boolean isEmailExist(String username) throws UserDAOException {
        String sql = "SELECT count(*) FROM users WHERE username = ?";
        User user = null;
        ResultSet rs;
        int count = 0;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps= connection.prepareStatement(sql)){
            //extract model info

            ps.setString(1, username);
            rs = ps.executeQuery();

            if(rs.next()){
               count = rs.getInt(1);
            }
            return count > 0;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL error in user valid for email: " + username);
        }
    }
}
