package com.example.demo.basket;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasketConfiguration {
	@Bean
	public HttpSessionListener createSessionListener() {
		return new HttpSessionListener() {
			@Override
			public void sessionCreated(HttpSessionEvent se) {
				HttpSession session = se.getSession();
				System.out.println("tworzę sesję nr " + session.getId());
				session.setAttribute("basket", new Basket());
			}
			
			@Override
			public void sessionDestroyed(HttpSessionEvent se) {
				HttpSession session = se.getSession();
				System.out.println("koniec sesji nr " + session.getId());
			}
		};
	}
}
