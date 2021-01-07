package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.basket.Basket;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductsRepository;

@Controller
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	private ProductsRepository productsRepository;
	
	@GetMapping
	public String listProducts(Model model) {
		List<Product> products = productsRepository.findAll();
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/{id}")
	public String oneProduct(Model model,
			@PathVariable("id") Integer productId) {
		Optional<Product> maybeProduct = productsRepository.findById(productId);
		if(maybeProduct.isPresent()) {
			model.addAttribute("product", maybeProduct.get());
			return "product";
		} else {
			model.addAttribute("id", productId);
			return "missing_product";
		}
	}
	
	@GetMapping("/{id}/add-to-basket")
	public String addToBasket(
			@PathVariable("id") Integer productId,
			@SessionAttribute Basket basket) {
		Optional<Product> maybeProduct = productsRepository.findById(productId);
		if(maybeProduct.isPresent()) {
			basket.incrementProduct(maybeProduct.get());
		}
		
		return "redirect:/products";
	}
	
	@GetMapping("/{id}/edit")
	public String showForm(Model model,
			@PathVariable("id") Integer productId) {
		Optional<Product> maybeProduct = productsRepository.findById(productId);
		if(maybeProduct.isPresent()) {
			model.addAttribute("product", maybeProduct.get());
			return "product_form";
		} else {
			model.addAttribute("id", productId);
			return "missing_product";
		}
	}
	
	@PostMapping("/{id}/edit")
	public String processForm(Model model,
			@PathVariable("id") Integer productId,
			@Valid @ModelAttribute(name="product") Product product,
			BindingResult bindingResult
		) {
		System.out.println("odebrany produkt: " + product );
		if(bindingResult.hasErrors()) {
			return "product_form";
		} else {
			productsRepository.save(product);
			System.out.println("zapisany produkt: " + product );
			return "redirect:/products/" + product.getProductId();
		}
	}
}
