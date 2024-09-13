package br.edu.fateccotia.bia.tasklist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.bia.tasklist.model.Category;
import br.edu.fateccotia.bia.tasklist.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryservice;
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Map<String, String> category) {
		String cat = category.get("category");
		String color = category.get("color");
		return ResponseEntity.ok(categoryservice.save(cat, color));
	}
	
	
	@GetMapping
	public ResponseEntity<List<Category>> listAll() {
		return ResponseEntity.ok(categoryservice.listAll());
	}
}
