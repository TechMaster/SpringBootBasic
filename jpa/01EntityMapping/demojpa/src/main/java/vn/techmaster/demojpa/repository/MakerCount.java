package vn.techmaster.demojpa.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MakerCount {
  private String maker;
  private long count; //Phải là kiểu long chứ không thể là int
}