package vn.techmaster.democookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
public class HomeController {


	@GetMapping("/")
    public String home(Model model,
                       HttpServletRequest request,
                       HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .forEach(c -> System.out.println(c.getName() + "=" + c.getValue()));
        }

        Cookie newCookie = new Cookie("testCookie", "testCookieValue");
        newCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(newCookie);
        model.addAttribute("msg", "test method msg");

        return "fragments/home";
    }

    @GetMapping("/test2")
    public String handleRequest (@CookieValue(value = "testCookie", defaultValue = "defaultCookieValue") String cookieValue, Model model) {
        System.out.println(cookieValue);
        model.addAttribute("cookieValue", cookieValue);
        return "fragments/home1";
    }
}

/*
*
*@CookieValue annotation maps the value of the cookie to the method parameter.
You should set the default value to avoid runtime exception when the cookie is not available.
*HttpServletResponse class can be used to set a new cookie in the browser.
You just need to create an instance of Cookie class and add it to the response.
* To read all cookies, you can use HttpServletRequest's getCookies() method which returns an array of Cookie.
Max-Age directive specifies the date and time, when the cookie should expire.
*If you are storing sensitive information in a cookie, make sure to set Secure and HttpOnly flags to avoid XSS attacks.
*Set the Path=/ to make a cookie accessible everywhere for current domain.
*To delete a cookie, set the Max-Age to 0 and pass all the properties you used to set it.
* */
