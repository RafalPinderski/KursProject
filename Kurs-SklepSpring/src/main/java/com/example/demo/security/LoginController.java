package com.example.demo.security;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginPage() {
		return "login-page";
	}

    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	// Obiekt sesji jest wstrzykiwany z kontekstu

    	// Na wszelki wypadek wylogowuję na dwa sposoby: przez setAuthentication(null) i przez session.invalidate().
    	// U mnie każdy z nich jest wystarczający, ale na StackOverflow pisali, że samo session.invalidate() nie wystarcza;
    	// z drugiej strony stosuję ogólne zalecenie dla aplikacji webowych, żeby po wylogowaniu czyścić sesję.
    	
    	SecurityContextHolder.getContext().setAuthentication(null);
    	session.invalidate();
        return "redirect:/";
    }
    
    @GetMapping("/whoami")
    public String whoAmI(Authentication authentication, Model model) {
    	// Zamiast wstrzykiwania parametru można też tak:
    	// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(authentication != null && authentication.isAuthenticated()) {
    		model.addAttribute("userName", authentication.getName());
    		model.addAttribute("authorities", authentication.getAuthorities());
    	}
    	return "whoami";
    }
}
