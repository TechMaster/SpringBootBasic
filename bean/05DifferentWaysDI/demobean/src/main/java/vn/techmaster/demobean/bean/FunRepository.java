package vn.techmaster.demobean.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class FunRepository {
  @Autowired
  public FunRepository(@Value("${engineType}") String engineType) {
    System.out.println("FunRepository :" + engineType);
  }  
}
