package br.edu.fateccotia.bia.tasklist.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.bia.tasklist.model.Token;
import br.edu.fateccotia.bia.tasklist.model.User;
import br.edu.fateccotia.bia.tasklist.repository.TokenRepository;
import br.edu.fateccotia.bia.tasklist.repository.UserRepository;

@Service
public class AuthService {
	private Integer TOKEN_TTL = 60;
	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private TokenRepository TokenRepository;
 
	public void signup(String email, String password) throws Exception {
		User user = new User();
		user.setEmail(email);
		user.setPassword(generateHash(password));
		
		Optional<User> userFound = UserRepository.findByEmail(email);
		if (userFound.isPresent()) {
			throw new Exception("E-mail already exists");
		}
		UserRepository.save(user);
		
	}
	
	public Token login(String email, String pwd) throws Exception {
		Optional<User> userFound = UserRepository.findByEmail(email);
		if (userFound.isPresent()) {
	        User user = userFound.get();
	        String hashedPassword = generateHash(pwd);
	        
	        if (hashedPassword.equals(user.getPassword())) {
	            System.out.println("Login bem-sucedido!");
	           
	            return generateToken(user);
	        } else {
	            throw new Exception("Senha incorreta");
	        }
	    } else {
	        throw new Exception("Usuário não encontrado");
	    }
	}
	

	private Token generateToken(User user) {
		Token token = new Token();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpirationTime(Instant.now().plusSeconds(TOKEN_TTL).toEpochMilli());

		TokenRepository.save(token);
		return token;
	}
	
	

	private String generateHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			return toHex(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	private String toHex(byte[] digest) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			sb.append(Integer.toString(digest[i]& 0xff + 0x100, 16).substring(1));
		}
		return sb.toString();	
	}

	public void signout(String token) {
		Optional<Token> found = TokenRepository.findByToken(token);
		found.ifPresent(t -> { 
				t.setExpirationTime(Instant.now().toEpochMilli()); 
				TokenRepository.save(t);
			});
		
	}

	public Boolean validate(String token) {
		Optional<Token> found = TokenRepository.findByToken(token);
		return found.isPresent() && found.get().getExpirationTime() > Instant.now().toEpochMilli();
	}
	
	


}
