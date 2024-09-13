package br.edu.fateccotia.bia.tasklist.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.bia.tasklist.model.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {

	Optional<Token> findByToken(String token);

}