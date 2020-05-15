package com.prueba.controller;
/*
import java.awt.PageAttributes.MediaType;
import java.sql.Date;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.dao.CourseDAO;
import com.prueba.entitys.Course;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@RestController
@RequestMapping("token")
public class TokenRest {

 
	@PostMapping
    public ResponseEntity<String> token() {
 
      long time = System.currentTimeMillis();
      String secretKey = "mySecretKey";
 
      final String jwt = Jwts.builder()
    		  .setId("softtekJWT")
    		  .setSubject("Gabriel")
    		  .setIssuedAt(new Date(time))
    		  .setExpiration(new Date(time+900000))
    		  .claim("email","example@example.com")
    		  //.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
    		  .signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
      return new ResponseEntity<>("Bearer "+jwt, HttpStatus.OK);

   }	

}
*/


import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.entitys.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class TokenRest {

	@PostMapping("token")
	public User login() {
		
		String token = getJWTToken();
		User user = new User();
		user.setUser("Gabriel");
		user.setToken(token);		
		return user;
		
	}

	private String getJWTToken() {
		long time = System.currentTimeMillis();
		String secretKey = "mySecretKey";
		
		String token = Jwts
				.builder()
	    		  .setId("softtekJWT")
	    		  .setSubject("Gabriel")
	    		  .setIssuedAt(new Date(time))
	    		  .setExpiration(new Date(time+900000))
	    		  .claim("email","example@example.com")
	    		  /*.claim("authorities",
							grantedAuthorities.stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))*/
	    		  //.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
	    		  .signWith(SignatureAlgorithm.HS512,
							secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}