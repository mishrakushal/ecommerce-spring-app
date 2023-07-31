package com.working.major.controller;

import com.working.major.dto.ProductDTO;
import com.working.major.model.Category;
import com.working.major.model.Product;
import com.working.major.service.CategoryService;
import com.working.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
	// ---getProperty("user.dir") gets us to the project directory---
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";


	// ---------------CATEGORIES--------------------------

	@Autowired
	CategoryService categoryService;

	@GetMapping("/admin")
	public String adminHome() {
		// returns string which is the name of the HTML file
		return "adminHome";
	}

	@GetMapping("/admin/categories")
	public String getAllCategories(Model model) {
		List<Category> categoryList = categoryService.getAllCategory();
		model.addAttribute("categories", categoryList);
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getAddCategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}


	@PostMapping("/admin/categories/add")
	public String postAddCategory(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);

		// update if category is present
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}

		// otherwise, return an ERROR page
		return "404";
	}



	// ---------------PRODUCTS--------------------------

	@Autowired
	ProductService productService;

	@GetMapping("/admin/products")
	public String getAllProducts(Model model) {
		List<Product> productsList =  productService.getAllProducts();
		model.addAttribute("products", productsList);
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String getAddProduct(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}

	@PostMapping("/admin/products/add")
	public String postAddProducts(@ModelAttribute("productDTO") ProductDTO productDTO,
								  @RequestParam("productImage") MultipartFile file,
								  @RequestParam("imgName") String imgName) throws IOException {

		Product product = new Product();

		/*	---------POPULATE NEW Product OBJECT USING ProductDTO---------	*/
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		product.setImageName(imgName);

		/*	---------STORE IMAGE LOGIC---------	*/
		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDir, imageUUID);
			Files.write(filePath, file.getBytes());
		} else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);

		/*	---------SAVE PRODUCT---------	*/
		productService.addProduct(product);

		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/update/{id}")
	public String getUpdateProduct(@PathVariable Long id, Model model) {
		Product product = productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();

		/*	---------POPULATE NEW ProductDTO OBJECT USING Product---------	*/
		// id, name, category, price, weight, description, imgName
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());

		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);

		return "productsAdd";
	}


}
