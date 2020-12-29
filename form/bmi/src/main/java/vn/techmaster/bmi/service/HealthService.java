package vn.techmaster.bmi.service;

import org.springframework.stereotype.Service;

import vn.techmaster.bmi.request.BMIRequest;
import vn.techmaster.bmi.response.BMIResult;

@Service
public class HealthService {

  // Phương thức này cũng không phải tốt lắm, vì nó phục thuộc vào 2 kiểu BMIRequest, BMIResult
  // Khi BMIRequest và BMI Result thay đổi, phương thức này cũng có thể bị ảnh hưởng
  public BMIResult calculateBMI(BMIRequest request) {
    float bmiIndex = request.getWeight() / (request.getHeight() * request.getHeight());
      String category;
      if (bmiIndex < 15) {
        category = "Ốm đói";
      } else if (bmiIndex < 16) {
        category = "Rất gầy";
      } else if (bmiIndex < 18.5) {
        category = "Thiếu cân";
      } else if (bmiIndex < 25) {
        category = "Chuẩn mực";
      } else if (bmiIndex < 30) {
        category = "Hơi béo";
      } else if (bmiIndex < 35) {
        category = "Rất béo";
      } else if (bmiIndex < 40) {
        category = "Khủng long";
      } else {
        category = "Gọi cần cẩu";
      }
      return new BMIResult(bmiIndex, category,"");
  }

}
