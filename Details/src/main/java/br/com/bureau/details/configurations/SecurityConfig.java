package br.com.bureau.details.configurations;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.bureau.details.queues.GetUserSender;
import br.com.bureau.details.security.CustomAuthorization;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private GetUserSender userSender;
	
	public static final String[] PUBLIC_MATCHERS = { "/h2-console/**", "/h2-console/header", "/images/**",
			"/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/**", "/csrf", "/details-api/h2-console/**",
			"/details-api/h2-console/header", "/details-api/images/**", "/details-api/swagger-ui.html",
			"/details-api/webjars/**", "/details-api/swagger-resources/**", "/details-api/v2/**", "/details-api/csrf" };
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.cors().and().authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
		http.addFilterAfter(new CustomAuthorization(this.userSender), BasicAuthenticationFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("*"));
		configuration.setAllowedMethods(Collections.singletonList("*"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		configuration.setExposedHeaders(Collections.singletonList(HttpHeaders.ACCEPT));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
