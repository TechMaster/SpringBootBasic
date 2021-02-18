package vn.techmaster.simpleauthen.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenProvider implements AuthenticationProvider {

  @Autowired
  private InMemoryUserDetailsManager inMemoryUserDetailsManager;
  
  @Autowired
  private PasswordEncoder encoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String rawPassword = String.valueOf(authentication.getCredentials());

    try {
      UserDetails userDetail = inMemoryUserDetailsManager.loadUserByUsername(username);      
      if (encoder.matches(rawPassword, userDetail.getPassword())) {
        return new UsernamePasswordAuthenticationToken (username, userDetail.getPassword(), userDetail.getAuthorities());
      } else {
        return null;
      }
    } catch (UsernameNotFoundException e) {
      return null;
    }    
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class .isAssignableFrom(authentication);
  }
  
}
