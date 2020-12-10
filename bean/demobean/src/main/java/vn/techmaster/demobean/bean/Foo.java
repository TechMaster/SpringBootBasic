package vn.techmaster.demobean.bean;

public class Foo implements ObjSay {
  private String msg;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Foo(String msg) {
    this.msg = msg;
  }

  
  public String saySomeThing() {
    return msg;
  }

}
