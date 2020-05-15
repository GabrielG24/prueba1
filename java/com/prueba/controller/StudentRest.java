package com.prueba.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.dao.StudentDAO;
import com.prueba.entitys.Student;

@RestController
@RequestMapping("students")
public class StudentRest {
	
		
	@Autowired
	private StudentDAO studentDAO;

	/*get all students paginated*/
	@GetMapping
	public Page<Student> getStudentPag(@PageableDefault(size=5,page=0) Pageable pageable){
		Page<Student> result=studentDAO.findAll(pageable);
		return result;
	}
	
	/*get all students*/
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getStudents(){
		List<Student> students=studentDAO.findAll();
		return ResponseEntity.ok(students);
	}
	
	/*get a student by id*/
	@GetMapping(value="{studentId}")
	public ResponseEntity getStudentById(@PathVariable("studentId") String studentId){
		
		Optional<Student> optionalStudent = studentDAO.findById(studentId);
		if(optionalStudent.isPresent()) {
			return ResponseEntity.ok(optionalStudent.get());	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND+ ": Student not found. ",HttpStatus.NOT_FOUND);
		}
			
	}	
	
	@PostMapping
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		//Student newStudent = studentDAO.save(student);
				
		Validations valid = new Validations();
		
		if(valid.isValidRut(student.getRut()) && student.getAge()>18 && student.getName()!="" ){
			studentDAO.save(student);
			return new ResponseEntity<>(HttpStatus.CREATED+ ": Student created. ",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST+ ": The data entered is not valid ",HttpStatus.BAD_REQUEST);
		}
			
	}
	
	@DeleteMapping(value="{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable("studentId") String studentId) {
		
		Optional<Student> optionalStudent = studentDAO.findById(studentId);
		
		if(optionalStudent.isPresent()) {
			studentDAO.deleteById(studentId);			
			return new ResponseEntity<>(HttpStatus.OK+ ": Student deleted. ",HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND+ ": Student not found. ",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="{studentId}")
	public ResponseEntity<Student> updateStudent(@PathVariable("studentId") String studentId,@RequestBody Student student) {

		Optional<Student> optionalStudent = studentDAO.findById(studentId);
		
		if(optionalStudent.isPresent()) {
			Student updateStudent= optionalStudent.get();
			updateStudent.setName(student.getName());
			updateStudent.setLastName(student.getLastName());
			updateStudent.setAge(student.getAge());
			updateStudent.setId_course(student.getId_course());
			studentDAO.save(updateStudent);
		
		return ResponseEntity.ok(updateStudent);	
		} else {
			return ResponseEntity.notFound().build();
		}
	}	
	

}
