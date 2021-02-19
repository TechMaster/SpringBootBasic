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

  @Autowired  //Phải nối vào InMemoryUserDetailsManager để tìm user theo Username
  private InMemoryUserDetailsManager inMemoryUserDetailsManager;
  
  @Autowired  //Dùng để kiểm tra password gửi lên trong login request với Hashed Password lưu trữ
  private PasswordEncoder encoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String rawPassword = String.valueOf(authentication.getCredentials());

    try {
      UserDetails userDetail = inMemoryUserDetailsManager.loadUserByUsername(username); //Tìm UserDetail theo Username
      if (encoder.matches(rawPassword, userDetail.getPassword())) { //So sánh password
      
        return new UsernamePasswordAuthenticationToken (username, userDetail.getPassword(), userDetail.getAuthorities());
      } else {
        return null;
      }
    } catch (UsernameNotFoundException e) {
      return null;
    }    
  }

  @Override // return true nếu CustomAuthenProvider hỗ trợ authentication kiểu Username, Password
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
  
}
