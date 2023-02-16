package com.example.demo.domain.service;

import com.example.demo.domain.dto.UpdateStudentDto;

import com.example.demo.domain.model.Student;

import java.util.List;

public interface IService {
    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentsById(String id);

    void deleteStudentById(String id);

    Student updateStudent(String id , UpdateStudentDto updateStudentDto);
}
