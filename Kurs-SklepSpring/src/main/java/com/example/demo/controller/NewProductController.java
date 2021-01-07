package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductsRepository;

@Controller
@RequestMapping("/add_new_product")
public class NewProductController {
	@Autowired
	private ProductsRepository productsRepository;
	
	@GetMapping
	public String showForm(@ModelAttribute(name="product") Product product) {
		return "product_form";
	}
	
	@PostMapping
	public String processForm(Model model, 
			@Valid @ModelAttribute(name="product") Product product,
			BindingResult bindingResult
		) {
		// @Valid powoduje wykonanie walidacji na wejściu do tej metody
		// BindingResult to obiekt zawierający informacje zwrotne nt walidacji.
		// Tym razem metoda zostanie wywołana nawet wtedy gdy walidacja się nie powiodła.
		
		System.out.println("odebrany produkt: " + product );
		if(bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			// Jeśli były błędy, to pozostaję na stronie formularza
			return "product_form";
		} else {
			productsRepository.save(product);
			System.out.println("zapisany produkt: " + product );
			// Jeśli był sukces, to przechodzę na inną stronę - stronę produktu.
			return "redirect:/products/" + product.getProductId();
		}
	}

}
