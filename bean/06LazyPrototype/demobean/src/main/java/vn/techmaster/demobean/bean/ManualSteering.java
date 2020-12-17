package vn.techmaster.demobean.bean;

import vn.techmaster.demobean.interfaces.Steering;

public class ManualSteering implements Steering {

  @Override
  public String steer() {
    return "Manual steering";
  }
}