package br.com.batalhao.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.batalhao.socialbooks.exceptions.AuthenticationRuntimeException;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws AuthenticationRuntimeException {
		try {
			auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
		} catch (Exception e) {
			throw new AuthenticationRuntimeException("Authentication Failure", e);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws AuthenticationRuntimeException {
		try {
			http.headers().frameOptions().sameOrigin();
		    http.csrf().ignoringAntMatchers("/h2-console/**");
		    http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		        
			http.authorizeRequests()
				.antMatchers("/livros/**").authenticated()
				.antMatchers("/autores/**").authenticated()
				.anyRequest().permitAll()
			.and()
				.httpBasic()
			.and()
				.csrf().disable();
		} catch (Exception e) {
			throw new AuthenticationRuntimeException("Authentication Failure", e);
		}
	}

}
