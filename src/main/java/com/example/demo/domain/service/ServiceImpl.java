package com.example.demo.domain.service;

import com.example.demo.domain.dto.UpdateStudentDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.data.repository.StudentRepoImpl;
import com.example.demo.domain.model.Student;

import java.util.List;


@Service("StudentService")
public class ServiceImpl implements IService{

    private final StudentRepoImpl studentRepo;


    ServiceImpl(@Qualifier("StudentRepo") StudentRepoImpl studentRepo){
        this.studentRepo = studentRepo;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepo.add(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.getAllStudents();
    }

    @Override
    public Student getStudentsById(String id) {
        return studentRepo.getStudentById(id);
    }

    @Override
    public void deleteStudentById(String id) {
        studentRepo.deleteStudentById(id);
    }

    @Override
    public Student updateStudent(String id , UpdateStudentDto updateStudentDto) {
        return studentRepo.updateStudent(id , Student.build(null , updateStudentDto.name() , updateStudentDto.email()));
    }


}
