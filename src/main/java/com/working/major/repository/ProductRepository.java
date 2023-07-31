package com.working.major.repository;

import com.working.major.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> getAllProductsByCategory_Id(int id);
}
