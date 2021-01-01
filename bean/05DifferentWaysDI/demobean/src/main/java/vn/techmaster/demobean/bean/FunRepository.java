package vn.techmaster.demobean.bean;

import org.springframework.stereotype.Repository;

public class FunRepository {
  public FunRepository(String engineType) {
    System.out.println("FunRepository :" + engineType);
  }  
}
