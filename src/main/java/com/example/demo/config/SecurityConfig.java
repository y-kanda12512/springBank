package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/login").permitAll()
						.requestMatchers("/signup").permitAll()
						.anyRequest().authenticated());
		return http.build();
	}

	//	//インメモリ認証
	@Bean
	public UserDetailsService users() {
		UserDetails admin = User
				.builder()
				.username(
						"admin")
				.password(passwordEncoder().encode("password"))
				.authorities(
						"ADMIN")
				.build();
		UserDetails user = User
				.builder()
				.username(
						"user")
				.password(passwordEncoder().encode("password"))
				.authorities(
						"USER")
				.build();
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	

	//	@Bean
	//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	//		http.authorizeHttpRequests(authorize -> authorize
	//				.requestMatchers("/login").permitAll()
	//				.requestMatchers("/signup").permitAll()
	//				.anyRequest().authenticated());
	//		return http.build();
	//	}
}
