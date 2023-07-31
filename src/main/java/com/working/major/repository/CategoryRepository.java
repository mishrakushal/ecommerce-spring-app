package com.working.major.repository;

import com.working.major.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/*
	<Category, Integer> means it will reference the Category objects
	using its ID which is of type Integer (int)
*/
public interface CategoryRepository extends JpaRepository <Category, Integer> {

}
