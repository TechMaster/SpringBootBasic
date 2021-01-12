package vn.techmaster.demojpa.model.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MakerCount {
  private String maker;
  private long count; //Phải là kiểu long chứ không thể là int
}