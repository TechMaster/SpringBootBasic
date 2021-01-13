package vn.techmaster.demojpa.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobCount {
  private String job;
  private long count; //Phải là kiểu long chứ không thể là int  
}
