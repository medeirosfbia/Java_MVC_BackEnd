package br.edu.fateccotia.bia.tasklist.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.bia.tasklist.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	public Optional<User> findByEmail(String email);
	
}
