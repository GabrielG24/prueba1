package com.prueba.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@Column(name="rut",length=12)
	private String rut;

	@Column(name="name",length=50)
	private String name;
	
	@Column(name="lastName",length=50)
	private String lastName;
	
	@Column(name="age",length=3)
	private int age;	
	
	@Column(name="id_course",length=4)
	private String id_course;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId_course() {
		return id_course;
	}

	public void setId_course(String id_course) {
		this.id_course = id_course;
	}

}