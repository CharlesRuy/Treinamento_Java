package br.com.cast.treinamento.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = { 
			"/swagger-resources/**", 
			"/swagger-ui.html", 
			"/v2/api-docs",
			"/webjars/**"};

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers(AUTH_WHITELIST).
		permitAll().
		anyRequest().
		authenticated();
	}

}
