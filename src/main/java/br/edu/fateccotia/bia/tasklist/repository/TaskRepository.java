package br.edu.fateccotia.bia.tasklist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.bia.tasklist.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
