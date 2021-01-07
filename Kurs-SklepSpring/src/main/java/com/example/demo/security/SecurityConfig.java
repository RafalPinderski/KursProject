package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; // użyj dataSource, który jest domyślny dla tej aplikacji
    
	private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	// Ta metoda ustala reguły autoryzacji (reguły dostępu)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/add_new_product", "/products/*/edit").hasAuthority("wlasciciel")
			.antMatchers("/", "/whoami", "/products/**", "/*.css").permitAll()
			.antMatchers("/login").anonymous() // nie może być zalogowany!
			.antMatchers("/logout").authenticated() // zalogowany jako ktokolwiek
			.anyRequest().denyAll() // pozostałe adresy blokujemy
			.and()
		.formLogin()
        	.loginPage("/login")
        	.defaultSuccessUrl("/")
        	.and()
        .logout()
        	.logoutUrl("/logout")
        	.logoutSuccessUrl("/login")
        	.and()
		.csrf().disable();
	}
	
	// Ta metoda ustala zasady uwierzytelnienia (authentication)
	// Czyli skąd się biorą użytkownicy.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("ala").password("{noop}abc123").roles("wlasciciel")
//			.and()
//			.withUser("ola").password("{noop}abc123").roles("pomocnik");

		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			// username, password, enabled
			.usersByUsernameQuery("SELECT username, password, enabled FROM merchands WHERE username = ?")
			// username, authority  -- czyli tak jakby rola
			.authoritiesByUsernameQuery("SELECT username, roles FROM merchands WHERE username = ?")
		;
	}
	
}
