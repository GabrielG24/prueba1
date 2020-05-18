package com.prueba.controller;

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
	    		  .claim("authorities",
							grantedAuthorities.stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))
	    		  //.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
	    		  .signWith(SignatureAlgorithm.HS512,
							secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
