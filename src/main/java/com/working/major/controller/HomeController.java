package com.working.major.controller;

import com.working.major.global.GlobalData;
import com.working.major.model.Product;
import com.working.major.service.CategoryService;
import com.working.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	@GetMapping({"/", "/home"})
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable int id, Model model) {
		List<Product> productList = productService.getAllProductsByCategoryId(id);
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productList);
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";
	}
}
