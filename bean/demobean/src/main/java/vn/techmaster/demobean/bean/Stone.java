package vn.techmaster.demobean.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stone {
  private String msg;

  public Stone() {
    Date date = new Date(); // This object contains the current date value
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    msg = formatter.format(date);

  }

  public String throwSomeThing() {
    return msg;
  }
}
