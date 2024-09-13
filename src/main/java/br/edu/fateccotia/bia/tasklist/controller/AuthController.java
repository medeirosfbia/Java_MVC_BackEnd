package br.edu.fateccotia.bia.tasklist.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.bia.tasklist.model.Token;
import br.edu.fateccotia.bia.tasklist.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Map<String, String> user) {
		String email = user.get("email");
		String pwd = user.get("password");
		try {
			authService.signup(email, pwd);
			return ResponseEntity.ok().build();		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
		
		
	@PostMapping("/login")
	public ResponseEntity<Token> login(@RequestBody Map<String, String> user) {
		try {
			Token token = authService.login(user.get("email"), user.get("password"));
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}		
	}
	
	@PostMapping("/signout")
	public ResponseEntity<Token> signout(@RequestHeader String token) {
		authService.signout(token);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> check(@RequestHeader String token) {
		Boolean isValid =  authService.validate(token);
		return (isValid) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	

//	@PostMapping("/check")
//	public ResponseEntity<?> check(@RequestBody Map<String, String> user) {
//		try {
//			authService.login(email, pwd);
//			return ResponseEntity.ok().build();		
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//		
//	}
	
}
