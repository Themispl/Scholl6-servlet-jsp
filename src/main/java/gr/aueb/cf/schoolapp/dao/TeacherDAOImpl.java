package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "insert into teachers (firstname, lastname) values (?, ?)";
        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps= connection.prepareStatement(sql)){
            //extract model info
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);

            ps.executeUpdate();
            //logging
            return teacher;

        }catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL error in teacher insert" + teacher);
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps= connection.prepareStatement(sql)){
            //extract model info
            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            ps.executeUpdate();
            //logging
            return teacher;

        }catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL error in teacher update" + teacher);
        }
    }

    @Override
    public void delete(Integer id) throws TeacherDAOException {
        String sql = "DELETE FROM teachers WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps= connection.prepareStatement(sql)){
            ps.setInt(1, id);

            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL error in teacher delete" + id);
        }
    }

    @Override
    public Teacher getById(Integer id) throws TeacherDAOException {
        String sql = "SELECT * FROM teachers WHERE id = ?";
        Teacher teacher = null;
        ResultSet rs;

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps= connection.prepareStatement(sql)){
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                teacher = new Teacher(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
            return teacher;

        }catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL error in teacher get by id:" + id);
        }
    }

    @Override
    public List<Teacher> getFilteredTeachers(String firstname, String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM teachers WHERE firstname LIKE ? AND lastname LIKE ?";
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs;
        Teacher teacher ;

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps= connection.prepareStatement(sql)){
            ps.setString(1, firstname + "%");
            ps.setString(2, lastname + "%");
            rs = ps.executeQuery();
            while(rs.next()){
                teacher = new Teacher(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
                teachers.add(teacher);
            }
            return teachers;

        }catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL error in teacher filter:");
        }
    }
}
