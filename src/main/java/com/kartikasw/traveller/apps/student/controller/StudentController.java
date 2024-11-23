package com.kartikasw.traveller.apps.student.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kartikasw.traveller.apps.student.model.StudentRequest;
import com.kartikasw.traveller.apps.student.service.IStudentService;
import com.kartikasw.traveller.model.GeneralResponse;
import com.kartikasw.traveller.model.MetadataResponse;
import com.kartikasw.traveller.model.ResponseBuilder;

/// Buatlah sebuah class Controller dengan nama StudentController
/// Di dalam Controller terdapat endpoint/API untuk membuat data siswa baru. 
/// Berikut endpointnya : /api/v1/addStudent
@RestController
@Profile("student")
@RequestMapping("/api/v1")
public class StudentController {

    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/addStudent")
    public ResponseEntity<GeneralResponse<MetadataResponse, String>> addStudent(@RequestBody StudentRequest request) {
        var result = studentService.save(request);
        var response = ResponseBuilder.responseBuilder(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
