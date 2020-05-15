package com.prueba.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.entitys.Student;

public interface StudentDAO extends JpaRepository<Student, String>{

}
