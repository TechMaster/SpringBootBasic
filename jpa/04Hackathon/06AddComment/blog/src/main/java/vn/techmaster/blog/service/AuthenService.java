package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import javax.servlet.http.Cookie;

import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.DTO.UserMapper;
import vn.techmaster.blog.controller.request.LoginRequest;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.UserRepository;

@Service
public class AuthenService implements IAuthenService {
  @Autowired
  private UserRepository userRepository;

  private static final String LOGIN_COOKIE = "loginsuccess";

  @Override
  public UserInfo login(LoginRequest loginRequest) throws AuthenException {
    var optionalUser = userRepository.findByEmail(loginRequest.getEmail());
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      if (!user.getPassword().equals(loginRequest.getPassword())) {
        throw new AuthenException("Wrong password");
      } else {
        return UserMapper.INSTANCE.userToUserInfo(user);
      }
    } else {
      throw new AuthenException("User with email " + loginRequest.getEmail() + " does not exist");
    }
  }

  public boolean isLogined(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (var cookie : cookies) {
        if (cookie.getName().equals(LOGIN_COOKIE)) {
          long userId = Long.parseLong(cookie.getValue());
          Optional<User> user = userRepository.findById(userId);
          return user.isPresent();
        }
      }
    }
    return false;
  }

  // Đây là giải pháp bảo mật hơn
  // https://stackoverflow.com/questions/4773609/what-is-a-relatively-secure-way-of-using-a-login-cookie
  public UserInfo getLoginedUser(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (var cookie : cookies) {
        if (cookie.getName().equals(LOGIN_COOKIE)) {
          long userId = Long.parseLong(cookie.getValue());
          Optional<User> optionalUser = userRepository.findById(userId);
          if (optionalUser.isPresent()) {
            return UserMapper.INSTANCE.userToUserInfo(optionalUser.get());
          } else {
            return null;
          }
        }
      }
    }
    return null;
  }

  public void setLoginedCookie(HttpServletResponse response, UserInfo user) {
    Cookie loginCookie = new Cookie(LOGIN_COOKIE, String.valueOf(user.getId()));
    loginCookie.setMaxAge(30 * 60);
    loginCookie.setPath("/");
    response.addCookie(loginCookie);
  }

  public void clearLoginedCookie(HttpServletResponse response) {
    Cookie loginCookie = new Cookie(LOGIN_COOKIE, null);
    loginCookie.setMaxAge(0);
    loginCookie.setHttpOnly(true);
    loginCookie.setPath("/");
    // add cookie to response
    response.addCookie(loginCookie);
  }

}
