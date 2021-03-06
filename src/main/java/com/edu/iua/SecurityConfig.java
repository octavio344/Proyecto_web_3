package com.edu.iua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edu.iua.auth.CustomTokenAuthenticationFilter;
import com.edu.iua.auth.PersistenceUserDetailService;
import com.edu.iua.authtoken.IAuthTokenBusiness;
import com.edu.iua.business.IUserBusiness;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired 
	private IAuthTokenBusiness authTokenBusiness;
	
	@Autowired 
	private IUserBusiness userBusiness;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PersistenceUserDetailService persistenceUserDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(persistenceUserDetailService);
	}
	
	private static final String[] AUTH_WHITELIST = {
	        "/swagger-resources/**",
	        "/swagger-ui.html",
	        "/v2/api-docs",
	        "/webjars/**"
	};

	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers(AUTH_WHITELIST);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/v1/users").anonymous()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/anonymous*").anonymous()
		.antMatchers("/swagger-ui/").anonymous()
		.antMatchers("/swagger-ui/*").anonymous()
		.antMatchers("/swagger-resources/*").anonymous()
		.antMatchers("/login*").permitAll()
		.antMatchers("/logout*").permitAll()
		.antMatchers("/").permitAll()
	 	.antMatchers("/index.html").permitAll()
		.antMatchers("/favicon.*").permitAll()	   
		.antMatchers("/ui/**").permitAll()
		

		.anyRequest().authenticated();
		
		http.addFilterAfter(new CustomTokenAuthenticationFilter(authTokenBusiness, userBusiness),UsernamePasswordAuthenticationFilter.class);
		
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	
	
}
