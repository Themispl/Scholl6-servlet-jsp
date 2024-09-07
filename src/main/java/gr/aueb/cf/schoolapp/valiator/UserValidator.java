package gr.aueb.cf.schoolapp.valiator;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.BaseUserDTO;
import gr.aueb.cf.schoolapp.dto.InsertUserDto;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class UserValidator<T> {

    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);
    private UserValidator() {}

    public static <T extends InsertUserDto> Map<String, String> validate(T dto) throws UserDAOException {
        Map<String, String> errors = new HashMap<String, String>();

        if (!dto.getPassword().equals(dto.getCorfimPassword())){
            errors.put("corfimPassword", "Passwords do not match");
        }
        if(dto.getPassword().length() <5 || dto.getPassword().length() > 32){
            errors.put("password", "Password must be between 5 and 32 characters");
        }
        if(dto.getUsername().matches("^.*\\s+.*$")){
            errors.put("username", "Username must contain at least one letter and without whitespace");
        }
        if(dto.getPassword().matches("^.*\\s+.*$")){
            errors.put("password", "Password must contain at least one letter and with whitespace");
        }
        return errors;

    }

}
