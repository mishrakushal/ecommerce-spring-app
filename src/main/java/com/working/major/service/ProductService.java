package com.working.major.service;

import com.working.major.model.Product;
import com.working.major.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public void addProduct (Product product) {
		productRepository.save(product);
	}

	public List<Product> getAllProducts () {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById (Long id) {
		return productRepository.findById(id);
	}

	public void deleteProductById (Long id) {
		productRepository.deleteById(id);
	}

	public List<Product> getAllProductsByCategoryId(int id) {
		return productRepository.getAllProductsByCategory_Id(id);
	}
}
