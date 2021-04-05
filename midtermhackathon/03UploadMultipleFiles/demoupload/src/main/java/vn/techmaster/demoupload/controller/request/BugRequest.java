package vn.techmaster.demoupload.controller.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BugRequest {
  private String name;
  public MultipartFile[] photos;
}