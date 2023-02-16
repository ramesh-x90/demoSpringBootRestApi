package com.example.demo.data.repository;

import com.example.demo.domain.model.Student;

import java.util.List;

public interface StudentRepo {

    /**
     * Create new user
     * @param student
     * @return : student
     */
    Student add(Student student);


    /**
     *
     * @return List of Students
     */
    List<Student> getAllStudents();


    /**
     * get student with given uuid
     * @param uuid : String
     * @return Student
     */
    Student getStudentById(String uuid);


    /**
     * delete student using uuid
     * @param uuid : String
     * @return void
     */
    void deleteStudentById(String uuid);


    /**
     * Update student
     * @param uuid : String
     * @return updated student
     * @Exceptions ResourceNotFoundException
     */
    Student updateStudent(String uuid , Student student);



}
