package com.working.major.service;

import com.working.major.model.Category;
import com.working.major.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
	/*
		Auto-wiring enables automatic dependency injection
		This eliminates the need for getter-setter
	 */
	@Autowired
	CategoryRepository categoryRepository;

	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	public List<Category> getAllCategory () {
		return categoryRepository.findAll();
	}

	public void deleteCategoryById (int id) {
		categoryRepository.deleteById(id);
	}

	public Optional<Category> getCategoryById (int id) {
		return categoryRepository.findById(id);
	}

	public void updateCategoryById (int id) {
		Optional<Category> categories = categoryRepository.findById(id);
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}
}
