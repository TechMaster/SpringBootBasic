package vn.techmaster.demoupload.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Person {
  private String name;
  private String email;
  public MultipartFile photo;
}