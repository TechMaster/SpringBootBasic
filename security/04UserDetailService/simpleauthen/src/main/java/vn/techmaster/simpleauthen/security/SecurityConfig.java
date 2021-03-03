package vn.techmaster.simpleauthen.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import vn.techmaster.simpleauthen.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomAuthenProvider authenticationProvider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.formLogin();

    http.authorizeRequests()
      .antMatchers(HttpMethod.GET, "/book").hasAuthority(Authority.READ)
      .antMatchers(HttpMethod.GET, "/book/add").hasAuthority(Authority.CREATE)
      .antMatchers(HttpMethod.POST, "/book/save").hasAuthority(Authority.CREATE)
      .antMatchers(HttpMethod.GET, "/book/{\\d+}").hasAuthority(Authority.READ)
      .antMatchers(HttpMethod.GET, "/book/edit/{\\d+}").hasAuthority(Authority.EDIT)
      .antMatchers(HttpMethod.GET, "/book/delete/{\\d+}").hasAuthority(Authority.DELETE)
      .antMatchers(HttpMethod.GET, "/book/search").hasAuthority(Authority.SEARCH)
      .antMatchers(HttpMethod.POST, "/book/search").hasAuthority(Authority.SEARCH)
      .antMatchers("/**").permitAll();
  }

  public static PasswordEncoder delegatePasswordEncoder(String encodingType) {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put("bcrypt", new BCryptPasswordEncoder());
    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
    encoders.put("scrypt", new SCryptPasswordEncoder());

    return new DelegatingPasswordEncoder(encodingType, encoders);
  }

  @Bean
  public PasswordEncoder encoder() {
    return SecurityConfig.delegatePasswordEncoder("bcrypt");
  }

  @Bean
  public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    Collection<UserDetails> users = new ArrayList<>();


    User tom = new User("tom@gmail.com", encoder().encode("123"));
    tom.setAuthority(Authority.READ, Authority.CREATE);


    User bob = new User("bob@gmail.com", encoder().encode("123"));
    bob.setAuthority(Authority.READ, Authority.SEARCH);

    User alice = new User("alice@gmail.com", encoder().encode("123"));
    alice.setAuthority(Authority.READ, Authority.DELETE);

    User jane = new User("jane@gmail.com", encoder().encode("123"));
    jane.setAuthority(Authority.READ, Authority.CREATE, Authority.EDIT);

    users.add(tom);
    users.add(bob);
    users.add(alice);
    users.add(jane);

    return new InMemoryUserDetailsManager(users);
  }

  

}
