package vn.techmaster.demorememberme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).
			withUser("thinhtv").password("$2a$04$Q2Cq0k57zf2Vs/n3JXwzmerql9RzElr.J7aQd3/Sq0fw/BdDFPAj.").roles("ADMIN");
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("thinhtv").password("123456").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Chỉ cho phép user đã đăng nhập mới được truy cập đường dẫn /admin/**
		http.authorizeRequests().antMatchers("/admin/**").authenticated();
		
		// Cấu hình remember me, thời gian là 1296000 giây
		http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//
				.loginProcessingUrl("/j_spring_security_login")//
				.loginPage("/login")//
				.defaultSuccessUrl("/admin")//
				.failureUrl("/login?message=error")//
				.usernameParameter("username")//
				.passwordParameter("password")
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?message=logout");

	}
}
