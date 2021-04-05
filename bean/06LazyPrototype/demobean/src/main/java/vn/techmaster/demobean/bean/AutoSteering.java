package vn.techmaster.demobean.bean;

import vn.techmaster.demobean.interfaces.Steering;

public class AutoSteering implements Steering {

  @Override
  public String steer() {
    return "Auto steering";
  }  
}

@Component
public class BeanA { }

@Component
public class BeanB { }

@Component
@DependsOn(value = {"beanA","beanB"})
public class BeanC { }
