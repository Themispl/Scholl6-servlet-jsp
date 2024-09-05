package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;

public interface ITeacherService {
    Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException;
    Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException;
}
