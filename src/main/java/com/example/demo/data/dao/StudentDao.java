package com.example.demo.data.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Student;

import java.util.UUID;


/**
 * Class for Access Student DataBase
 */
public interface StudentDao extends JpaRepository<Student, UUID>{
    
}
