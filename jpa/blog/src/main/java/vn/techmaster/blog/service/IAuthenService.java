package vn.techmaster.blog.service;

import vn.techmaster.blog.controller.request.LoginRequest;

public interface IAuthenService {
  public void login(LoginRequest loginRequest) throws AuthenException;
}
