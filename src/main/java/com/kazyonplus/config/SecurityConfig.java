package com.kazyonplus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins ={"http://localhost:4200/", "https://legalsystem.netlify.app/"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {



	@Autowired
	UserDetailsService userDetailsService;


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {


		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();


	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.cors().and()
				.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST).hasAnyRole("ADMIN","MANAGER","CONTRACT","BRANCH","SUPER","LICENSE","PROCURATION","CASE")
				.antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN", "MANAGER")
				.antMatchers(HttpMethod.DELETE).hasAnyRole("MANAGER")
				.antMatchers(HttpMethod.GET, "/v1/validate").hasRole("SUPER")
				.antMatchers(HttpMethod.GET, "/v1/users/**").hasAnyRole("ADMIN", "MANAGER", "CONTRACT","SUPER")
				/**CONTRACT**/

				.antMatchers("/contract/all").hasAnyRole("CONTRACT","SUPER")
				.antMatchers("/contract/store-codes").hasAnyRole("CONTRACT","SUPER")
				.antMatchers("/contract/filter").hasAnyRole("CONTRACT","SUPER")
				.antMatchers("/contract/status").hasAnyRole("CONTRACT","SUPER")
				.antMatchers("/contract/del").hasAnyRole("CONTRACT","SUPER")
				.antMatchers("/contract/downloadFile/**").permitAll()
				/**PROCURTION**/

				.antMatchers("/procuration/add").hasAnyRole("PROCURATION","SUPER")
				.antMatchers("/procuration/view/**").hasAnyRole("PROCURATION","SUPER")
				.antMatchers("/procuration/getall").hasAnyRole("PROCURATION","SUPER")
				.antMatchers("/procuration/search").hasAnyRole("PROCURATION","SUPER")
				.antMatchers("/procuration/update/**").hasAnyRole("PROCURATION","SUPER")
				.antMatchers("/procuration/delete/**").hasAnyRole("PROCURATION","SUPER")
				.antMatchers("/procuration/addFile/**").permitAll()
				.antMatchers("/procuration/downloadFile/**").permitAll()
				/***********************/
				/**CASE**/
				.antMatchers("/case").hasAnyRole("CASE","SUPER")
				.antMatchers("/case/downloadFile/**").permitAll()
				.antMatchers("/case/addFile/**").permitAll()

				/*******/
				.antMatchers("/session/**").hasAnyRole("CASE","SUPER")
				.antMatchers(HttpMethod.GET,"/branch/**").hasAnyRole("BRANCH","SUPER")
				.antMatchers(HttpMethod.GET,"/license/**").hasAnyRole("LICENSE","SUPER")
				.antMatchers(HttpMethod.GET,"/attachment/**").hasAnyRole("SUPER")



		//.antMatchers(HttpMethod.GET,"/v1/users/{userId}").access("@userSecurity.hasUserId(authentication,#userId)")
		;



	/*	http.cors().disable().httpBasic();

		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
*/
		super.configure(http);



	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/h2-console/**");
	}















}











