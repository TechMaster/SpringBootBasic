package vn.techmaster.demoupload.controller.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PersonRequest {
  private String name;
  private String email;
  public MultipartFile photo;
}