package com.prueba.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	
	@Id
	@Column(name="id_course",length=4)
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id_course;
	
	@Column(name="name",length=50)
	private String name;

	public String getId_course() {
		return id_course;
	}

	public void setId_course(String id_course) {
		this.id_course = id_course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
