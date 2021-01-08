package vn.techmaster.authen.service;

import java.util.List;

import vn.techmaster.authen.model.User;

public interface IAuthenService {
  public boolean login(String email, String password); //return true if login success
  public void createAccount(String fullname, String email, String password) throws AuthenException;
  public void updateAccount(String fullname, String email) throws AuthenException;
  public String hashPassword(String password);  //https://github.com/patrickfav/bcrypt
  public User findUserByID(String userid);
  public User findUserByEmail(String email);
  public boolean isAdmin(String userid);
  public boolean hasRole(String userid, String role);
  public void updateRole(String userid, List<String> roles);
  public List<String> getRoles(String userid);
}
