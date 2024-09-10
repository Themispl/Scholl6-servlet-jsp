package gr.aueb.cf.schoolapp.valiator;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.BaseDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidetor<T> {
    private final static ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final static ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    private TeacherValidetor(){}

    public static <T extends BaseDTO>Map<String, String> validate(T dto) throws TeacherDAOException{
        Map<String, String> errors = new HashMap<>();
        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "Το όνομα δεν πρέπει να περιλαμβάνει κενά");
        }
        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "Το επώνυμο δεν πρέπει να περιλαμβάνει κενά");
        }
        if(dto.getFirstname().length() < 2 || dto.getFirstname().length() > 32){
            errors.put("firstname", "Το όνομα πρέπει να ειναι 2 εως 32 χαρακτήρες");
        }
        if(dto.getLastname().length() < 2 || dto.getLastname().length() > 32){
            errors.put("larstname", "Το επώνυμο πρέπει να ειναι 2 εως 32 χαρακτήρες");
        }
        return errors;
    }
}
