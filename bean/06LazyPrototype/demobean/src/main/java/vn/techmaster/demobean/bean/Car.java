package vn.techmaster.demobean.bean;

//import org.springframework.beans.factory.ObjectFactory;
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


  /*
  // Trong trường hợp không muốn dùng @Lazy, thì cách tiếp theo là sử dụng ObjectFactory. Khi getObject được gọi, spring sẽ khởi tạo 1 instance mới cho prototype bean
  @Autowired
  private ObjectFactory<StreetMap> streetMapFactory;

  public String navigate() {
    return streetMapFactory.getObject().getRoute();
  }
  */
}