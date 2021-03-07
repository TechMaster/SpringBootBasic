package vn.techmaster.securityjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import vn.techmaster.securityjpa.security.CustomAuthenFailureHandler;
import vn.techmaster.securityjpa.security.CustomLogoutSuccessHandler;
import vn.techmaster.securityjpa.service.SecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityService securityService;

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
    .loginPage("/login")
    .loginProcessingUrl("/perform_login")
    .defaultSuccessUrl("/login_success", true)
    .failureUrl("/login_error")
    .failureHandler(new CustomAuthenFailureHandler())
    .and()
    .logout()
    .logoutUrl("/perform_logout")
    .logoutSuccessUrl("logout_success")
    .invalidateHttpSession(true)
    .deleteCookies("JSESSIONID")
    .logoutSuccessHandler(logoutSuccessHandler())
    .and()
    .rememberMe().key("RememberMeOption").tokenValiditySeconds(7 * 24 * 60 * 60); //7 days x 24 hours x 60 minutes x 60 seconds

    http.authorizeRequests()
      .mvcMatchers("/admin").hasAuthority("ADMIN")
      .mvcMatchers("/member").hasAnyAuthority("ADMIN", "USER", "AUTHOR", "EDITOR")
      .mvcMatchers("/author").hasAnyAuthority("AUTHOR")
      .mvcMatchers("/user").hasAnyAuthority("USER")
      .mvcMatchers("/editor").hasAnyAuthority("EDITOR")
      .mvcMatchers("/public").permitAll()
      .mvcMatchers("/h2-console/**").permitAll()
      .and().csrf().ignoringAntMatchers("/h2-console/**") //https://jessitron.com/2020/06/15/spring-security-for-h2-console/
      .and().headers().frameOptions().sameOrigin();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(securityService).passwordEncoder(encoder());
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
      return new CustomLogoutSuccessHandler();
  }
}
