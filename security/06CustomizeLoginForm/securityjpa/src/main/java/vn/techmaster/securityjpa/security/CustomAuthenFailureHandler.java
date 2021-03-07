package vn.techmaster.securityjpa.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenFailureHandler implements AuthenticationFailureHandler{

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
        System.out.println("Failed to authenticate");
        response.sendRedirect("/login_error");
  }
  
}
