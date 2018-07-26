package com.vishal.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vishal.login.repositories.UserRepository;
import com.vishal.login.service.CustomUserDetailService;

@EnableGlobalMethodSecurity(prePostEnabled=true) // to use hasAnyRole('ADMIN')
@EnableWebSecurity // to enable web security
@EnableJpaRepositories(basePackageClasses = UserRepository.class) // to locate entity
@Configuration
public class SecurityCongiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("**/secured/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin().permitAll();
	}



	private PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence arg0, String arg1) {
				
				return true;
			}
			
			@Override
			public String encode(CharSequence arg0) {
			
				return arg0.toString();
			}
		};
	}

	
}
