package com.wipro.onlineAssessmentSystem.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.wipro.onlineAssessmentSystem.repository.userRepository;
import com.wipro.onlineAssessmentSystem.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationSuccessHandler successHandler;

	@Autowired
	private userRepository userRepo;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/user/**").hasAnyAuthority("USER")
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN").and().formLogin().loginPage("/login").usernameParameter("username")
				.successHandler(successHandler)
				.failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							org.springframework.security.core.AuthenticationException exception)
							throws IOException, ServletException {
						// TODO Auto-generated method stub
						String email = request.getParameter("username");
						if(userRepo.findById(email).isPresent()) {
							response.sendRedirect("http://localhost:8085/passwordErrorPage");
						}else {
							response.sendRedirect("http://localhost:8085/invalidUserPage");	
						}
					}
				}).permitAll().and().logout().logoutSuccessUrl("/login");

		http.headers().frameOptions().disable();

	}
}
