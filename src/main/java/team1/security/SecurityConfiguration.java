package team1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import team1.service.DatabaseUserDetailsService;

@Configuration
public class SecurityConfiguration {

	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}

	@Bean
	DaoAuthenticationProvider autenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;

	}

	// Quali ruoli hanno autorizzazione a quali aree
	@Bean
	SecurityFilterChain filteChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ADMIN").antMatchers("/agent/{id}").hasAnyAuthority("AGENT", "ADMIN")
				.antMatchers("/appointment/appointmentListAdmin").hasAnyAuthority("ADMIN").antMatchers("/").permitAll()
				.and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		return http.build();
	}

}
