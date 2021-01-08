package vn.techmaster.authen.service;

import java.util.List;

import vn.techmaster.authen.model.User;

public class AuthenService implements IAuthenService {

  @Override
  public void createAccount(String fullname, String email, String password) throws AuthenException {
    // TODO Auto-generated method stub

  }

  @Override
  public User findUserByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User findUserByID(String userid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getRoles(String userid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean hasRole(String userid, String role) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String hashPassword(String password) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isAdmin(String userid) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean login(String email, String password) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void updateAccount(String fullname, String email) throws AuthenException {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateRole(String userid, List<String> roles) {
    // TODO Auto-generated method stub

  }
  
}
