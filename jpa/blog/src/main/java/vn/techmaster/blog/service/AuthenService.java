package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;

import vn.techmaster.blog.controller.request.LoginRequest;
import vn.techmaster.blog.repository.UserRepository;

public class AuthenService implements IAuthenService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void login(LoginRequest loginRequest) throws AuthenException {
    

  }
  
}
