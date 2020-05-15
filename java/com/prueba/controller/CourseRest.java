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

import com.prueba.dao.CourseDAO;
import com.prueba.entitys.Course;
import com.prueba.entitys.Student;

@RestController
@RequestMapping("courses")
public class CourseRest {
	
	@Autowired
	private CourseDAO courseDAO;

	/*get all courses paginated*/
	@GetMapping
	public Page<Course> getCoursePag(@PageableDefault(size=5,page=0) Pageable pageable){
		Page<Course> result=courseDAO.findAll(pageable);
		return result;
	}
	
	/*get all courses*/
	@GetMapping("/all")
	public ResponseEntity<List<Course>> getCourses(){
		List<Course> courses=courseDAO.findAll();
		return ResponseEntity.ok(courses);
	}
	
	/*get a course by id*/
	@GetMapping(value="{courseId}")
	public ResponseEntity getCourseById(@PathVariable("courseId") String courseId){
		
		Optional<Course> optionalCourse = courseDAO.findById(courseId);
		if(optionalCourse.isPresent()) {
			return ResponseEntity.ok(optionalCourse.get());	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND+ ": Course not found. ",HttpStatus.NOT_FOUND);
		}
			
	}	
	

	@PostMapping
	public ResponseEntity<String> createCourse(@RequestBody Course course) {
		//Course newCourse = courseDAO.save(course);
		
		if(course.getId_course().length()<=4 && course.getId_course()!="" && course.getName()!="" ){
			courseDAO.save(course);
			return new ResponseEntity<>(HttpStatus.CREATED+ ": Course created. ",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST+ ": The data entered is not valid ",HttpStatus.BAD_REQUEST);
		}			
	}	
	
	
	@DeleteMapping(value="{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable("courseId") String courseId) {
		
		Optional<Course> optionalCourse = courseDAO.findById(courseId);
		
		if(optionalCourse.isPresent()) {
			courseDAO.deleteById(courseId);		
			return new ResponseEntity<>(HttpStatus.OK+ ": Course deleted. ",HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND+ ": Course not found. ",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable("courseId") String courseId,@RequestBody Course course) {

		Optional<Course> optionalCourse = courseDAO.findById(courseId);
		
		if(optionalCourse.isPresent()) {
			Course updateCourse= optionalCourse.get();
			updateCourse.setName(course.getName());
			courseDAO.save(updateCourse);
		
		return ResponseEntity.ok(updateCourse);	
		} else {
			return ResponseEntity.notFound().build();
		}
	}	
	

}
