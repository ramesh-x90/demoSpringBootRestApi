package com.example.demo.presentation;

import com.example.demo.domain.dto.NewStudentDto;
import com.example.demo.domain.dto.UpdateStudentDto;
import com.example.demo.domain.model.Student;
import com.example.demo.domain.service.IService;


import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import jakarta.validation.Valid;



@RequestMapping("api/v1/student")
@RestController
public class StudentApiControllerImpl {

    private final IService studentService;

    StudentApiControllerImpl(@Qualifier("StudentService") IService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @NotNull @RequestBody NewStudentDto newStudentDto) {
        return ResponseEntity.ok(studentService.createStudent( Student.build(null,newStudentDto.name(),newStudentDto.email())));
    }

    @GetMapping
    public ResponseEntity<Object> retrieveAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@Valid @NotNull @PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudentsById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent( @Valid  @NotNull @PathVariable String id) {
        studentService.deleteStudentById(id);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@Valid @NotNull @PathVariable String id ,@RequestBody @Valid UpdateStudentDto updateStudentDto){
        return ResponseEntity.ok(studentService.updateStudent(id,updateStudentDto));


    }


}
