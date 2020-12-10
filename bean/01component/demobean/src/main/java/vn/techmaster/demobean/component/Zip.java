package vn.techmaster.demobean.component;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Zip {
  private String startDate;
  public Zip() {
    Date date = new Date(); // This object contains the current date value
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    startDate = formatter.format(date);
  }

  @Override
  public String toString() {
    return "startDate=" + startDate;
  }
}