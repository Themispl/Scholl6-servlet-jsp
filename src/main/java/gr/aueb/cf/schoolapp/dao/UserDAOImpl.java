package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.security.SecUtil;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            throw new UserDAOException("SQL error in teacher insert" + user);
        }
    }

    @Override
    public User getByUsername(String username) throws UserDAOException {
        return null;
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        return false;
    }

    @Override
    public boolean isEmailExist(String username) throws UserDAOException {
        return false;
    }
}
