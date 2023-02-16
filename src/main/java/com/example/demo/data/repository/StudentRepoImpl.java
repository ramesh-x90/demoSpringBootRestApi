package com.example.demo.data.repository;



import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import com.example.demo.data.dao.StudentDao;
import com.example.demo.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * <p> Access student data </p>
 * Actual layer that abstract <code>database</code> | <code>caching</code> | <code>api calls</code> | etc
 */
@Repository("StudentRepo")
public class StudentRepoImpl implements StudentRepo{

    private final StudentDao studentDao;


    StudentRepoImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }
    

    public Student add(Student student){
        return studentDao.save(student);
    }


    public List<Student> getAllStudents(){
        return studentDao.findAll();
    }


    public Student getStudentById(String uuid){
        return studentDao.findById(UUID.fromString(uuid)).orElse(null);
    }


    public void deleteStudentById(String uuid){
        studentDao.deleteById(UUID.fromString(uuid));
    }


    public Student updateStudent(String uuid , Student student){
        Optional<Student> st = studentDao.findById(UUID.fromString(uuid));

        if(st.isEmpty()){
            throw new ResourceNotFoundException("student with id = %s not found.".formatted(uuid));
        }

        st.ifPresent( s -> {

            if (student.getName() != null && !student.getName().isBlank()) {
                s.setName(student.getName());
            }

            if (student.getEmail() != null && !student.getEmail().isBlank()) {
                s.setEmail(student.getEmail());
            }

            studentDao.save(s);

        });

        return st.get();


    }

}
