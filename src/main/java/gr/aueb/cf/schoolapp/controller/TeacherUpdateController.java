package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.valiator.TeacherValidetor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/teachers/update")
public class TeacherUpdateController extends HttpServlet {
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(id, firstName, lastName);
        request.setAttribute("updateDTO", updateDTO);
        request.getRequestDispatcher("/WEB-INF/jsp/teachers-update.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(id, firstName, lastName);
        //Data Binding

        String errorMessage = "";
        Map<String, String> errors;

        String firstnameMessage;
        String lastnameMessage;

        Teacher teacher;

        try{

            errors = TeacherValidetor.validate(updateDTO);

            if(!errors.isEmpty()){
                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");

                request.setAttribute("firstnameMessage", firstnameMessage);
                request.setAttribute("lastnameMessage", lastnameMessage);

                request.setAttribute("updateDTO", updateDTO);
                request.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp").forward(request, response);
                return;
            }
            teacher = teacherService.updateTeacher(updateDTO);
            TeacherReadOnlyDTO teacherReadOnlyDTO = mapToTeacherReadOnlyDTO(teacher);

            request.setAttribute("teacherInfo", teacherReadOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-updated.jsp").forward(request, response);

        }catch (TeacherDAOException | TeacherNotFoundException e){
            errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp").forward(request, response);
        }
    }

    private TeacherReadOnlyDTO mapToTeacherReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
    }
}