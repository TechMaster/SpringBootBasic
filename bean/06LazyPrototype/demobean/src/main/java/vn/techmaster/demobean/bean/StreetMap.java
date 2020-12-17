package vn.techmaster.demobean.bean;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class StreetMap {

  //Tại sao tôi lại dùng static và final ở đây?
  private static final String[] froms = { "79 Mai Hắc Đế, Hai Bà Trưng", "15 Đoàn Trần Nghiệp, Hai Bà Trưng",
      "18 Hào Nam, Đống Đa", "2 Hàng Bạc, Hoàn Kiếm", "48 Tố Hữu, Nam Từ Liêm" };
  private static final String[] tos = { "Sân bay Nội Bài", "Hồ Đồng Đò", "Sân Gold Tam Đảo", "Royal City",
      "Aeon mall Hà Đông", "Vinmec", "Cầu Long Biên" };
  
  private Random rand; //Đối tượng sinh số ngẫu nhiên

  private String route; //Mô tả tuyến đường từ A đến B

  public StreetMap() {  
    try { //Khởi tạo bộ sinh số ngẫu nhiên
      rand = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {      
      e.printStackTrace();
    }
    // Tạo ra tuyến ngẫu nhiên từ điểm A thuộc mảng froms, đến điểm B thuộc mảng tos
    this.route = "From: <b>" + getRamdomFromArrayString(froms) + "</b> -> To: <b>" + getRamdomFromArrayString(tos) + "</b>";
  }

  public String getRoute() {
    return this.route;
  }

  //Hàm generic lấy kiểu đầu vào T bất kỳ
  private <T> T getRamdomFromArrayString(T[] array) {
    int index = this.rand.nextInt(array.length);
    return array[index];
  }
  
}
