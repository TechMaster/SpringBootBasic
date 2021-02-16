package vn.techmaster.simpleauthen.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.simpleauthen.model.Product;

@RestController

@RequestMapping("/api")
public class APIController {

  //
  @GetMapping("/products")
  public List<Product> getProducts() {
    List<Product> result = new ArrayList<>();
    result.add(new Product("Coffe Machine", 150));
    result.add(new Product("Apple Watch", 250));
    result.add(new Product("Eink Book Reader", 350));
    return result;
  }

  //Only Admin or Operator can see this
  @GetMapping("/backoffice")
  public String getSensitiveData() {
    return "Sensitive Data";
  }

  //Only Admin can see this
  @GetMapping("/secret")
  public String getSecretData() {
    return "OX-13";
  }
}
