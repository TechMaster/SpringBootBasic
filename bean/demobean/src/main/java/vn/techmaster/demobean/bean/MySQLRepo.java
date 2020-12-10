package vn.techmaster.demobean.bean;

public class MySQLRepo implements DBRepo{

  @Override
  public String connectTo() {    
    return "Connect to MySQL";
  }
  
}
