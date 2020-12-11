package vn.techmaster.demobean.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.techmaster.demobean.bean.Engine;

@Component
public class Car {
  @Autowired
  private Engine engine;  
  
  public String getEngineModel() {
    return engine.getModel();
  }

}
