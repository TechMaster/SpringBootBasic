package vn.techmaster.demobean.component;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Zip {
  private String startDate;
  public Zip() {// Lưu lại thời điểm đối tượng Zip được khởi tạo
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    startDate = formatter.format(date);
  }

  @Override
  public String toString() {
    return "startDate=" + startDate;
  }
}