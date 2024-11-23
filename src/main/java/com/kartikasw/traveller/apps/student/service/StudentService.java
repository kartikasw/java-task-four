package com.kartikasw.traveller.apps.student.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartikasw.traveller.apps.student.entity.Student;
import com.kartikasw.traveller.apps.student.model.StudentRequest;
import com.kartikasw.traveller.apps.student.repository.StudentRepository;
import com.kartikasw.traveller.exception.BusinessException;
import com.kartikasw.traveller.util.ErrorMessage;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public String save(StudentRequest request) {
        validateStudentRequest(request);
        mapper(request);
        return "Success";
    }

    private void mapper(StudentRequest request) {
        var student = new Student();
        student.setName(request.getName());
        student.setAddress(request.getAddress());
        repository.save(student);
    }

    private void validateStudentRequest(StudentRequest request) {
        if (Objects.isNull(request)) {
            var error = String.format(ErrorMessage.ERROR_NULL_OBJECT, Student.OBJECT_NAME);
            throw new BusinessException(error);
        }

        if (Objects.isNull(request.getName())) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, Student.NAME_FIELD);
            throw new BusinessException(error);
        }

        if (Objects.isNull(request.getAddress())) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, Student.ADDRESS_FIELD);
            throw new BusinessException(error);
        }
    }
}
