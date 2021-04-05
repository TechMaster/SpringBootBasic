package vn.techmaster.demobean.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import vn.techmaster.demobean.interfaces.Steering;

public class Car {
  private Engine engine;

  @Autowired
  @Qualifier("manualsteering")
  private Steering steering; //Field based Dependency Injection

  @Autowired
  @Lazy
  private StreetMap streetMap;

  public Car(Engine engine) { 
    this.engine = engine;
  }

  @Override
  public String toString() {
    return "Car [engine=" + engine + ", steering=" + steering.steer() + "]";
  }

  //Trả về điểm bắt đầu và kết thúc trong hành trình
  public String navigate() {
    return streetMap.getRoute();
  }
}