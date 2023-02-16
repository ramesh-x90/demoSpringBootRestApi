package com.example.demo;

import com.example.demo.data.dao.StudentDao;
import com.example.demo.domain.dto.UpdateStudentDto;
import com.example.demo.domain.model.Student;
import com.example.demo.domain.service.IService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DemoApplicationTests {

	@Autowired
	@Qualifier("StudentService")
	IService service;


	String name = "test-user";
	Student s;
	String email = "test-user@gmail.com";


	@Test
	void contextLoads() {

	}

	@Test
	void newUser(){
		s = service.createStudent( Student.build(null , name , email));
		assertEquals(name , s.getName() );
		assertEquals(email , s.getEmail() );

		service.deleteStudentById(s.getId().toString());
		assertNull(service.getStudentsById(s.getId().toString()));


	}


	@Test
	void getStudentList(){
		s = service.createStudent( Student.build(null , name , email));

		List<Student> ls = service.getAllStudents();
		assertEquals(false , ls.isEmpty());

		service.deleteStudentById(s.getId().toString());
		assertNull(service.getStudentsById(s.getId().toString()));
	}

	@Test
	void getStudentByID(){
		s = service.createStudent( Student.build(null , name , email));

		Student s1 = service.getStudentsById(s.getId().toString());

		assertEquals(name , s1.getName());
		assertEquals(email , s1.getEmail());

		service.deleteStudentById(s1.getId().toString());
		assertNull(service.getStudentsById(s1.getId().toString()));
	}


	@Test
	void duplicates(){

		s = service.createStudent( Student.build(null , name , email));

		assertThrows( Exception.class , () -> {
			service.createStudent( Student.build(null , name , email));
		});

		service.deleteStudentById(s.getId().toString());
		assertNull(service.getStudentsById(s.getId().toString()));
	}


	@Test
	void updateStudent(){
		s = service.createStudent( Student.build(null , name , email));

		String updatedName = "test-update-user";
		String updatedEmail = "test-update-email@gmail.com";

		s = service.updateStudent(s.getId().toString() , new UpdateStudentDto(updatedName, updatedEmail));

		assertEquals(updatedName , s.getName());
		assertEquals(updatedEmail , s.getEmail());


		service.deleteStudentById(s.getId().toString());
		assertNull(service.getStudentsById(s.getId().toString()));
	}


	@Test
	void deleteStudent(){
		s = service.createStudent( Student.build(null , name , email));

		service.deleteStudentById(s.getId().toString());
		assertNull(service.getStudentsById(s.getId().toString()));
	}



}
