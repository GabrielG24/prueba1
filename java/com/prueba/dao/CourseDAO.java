package com.prueba.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.entitys.Course;

public interface CourseDAO extends JpaRepository<Course, String>{

}
