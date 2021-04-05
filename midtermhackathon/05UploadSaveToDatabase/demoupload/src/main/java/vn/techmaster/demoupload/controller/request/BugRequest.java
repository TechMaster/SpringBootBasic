package vn.techmaster.demoupload.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.demoupload.model.Status;

import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugRequest {
  private String title;
  private Status status;
  public MultipartFile[] photos;
}