package vn.techmaster.demobean.bean;

public class OracleRepo implements DBRepo {
  @Override
  public String connectTo() {
    return "Connect to Oracle";
  }  
}
