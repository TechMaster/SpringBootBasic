package vn.techmaster.demofilemanager.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import vn.techmaster.demofilemanager.service.FileService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
        // Origins White-List
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
        		"http://localhost:8080",
                "http://localhost:27900"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
	}

	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.httpFirewall(defaultHttpFirewall());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers()
				.frameOptions()
				.sameOrigin()
			.and()
				.csrf()
				.ignoringAntMatchers("/api/**")
				.ignoringAntMatchers("/error/**")
			.and()
				.cors()
				.configurationSource(corsConfigurationSource())
			.and()
				.authorizeRequests()
				.antMatchers("/" + FileService.SAFE_DIR_NAME + "/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling().accessDeniedPage("/error")
			.and().httpBasic();
	}

}
