package com.working.major.controller;

import com.working.major.global.GlobalData;
import com.working.major.model.Product;
import com.working.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
	@Autowired
	ProductService productService;

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable Long id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}

	@GetMapping("/cart")
	public String getCart(Model model) {
		model.addAttribute("cart", GlobalData.cart);
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

		return "cart";
	}

	@GetMapping("/cart/removeItem/{index}")
	public String removeItemFromCart(@PathVariable int index, Model model) {
		/*	Remove product from Cart using its ID	*/
		GlobalData.cart.remove(index);

		model.addAttribute("cart", GlobalData.cart);
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "cart";
	}

	@GetMapping("/checkout")
	public String checkout (Model model) {
		model.addAttribute("cart", GlobalData.cart);
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}

}
