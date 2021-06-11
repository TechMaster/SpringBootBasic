package vn.techmaster.bmi.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.bmi.request.BMIRequest;
import vn.techmaster.bmi.response.BMIResult;

@Controller
@RequestMapping("/bmi2")
public class BMIController2 {
  @GetMapping
  public String getBMIForm(Model model) {
    model.addAttribute("bmiRequest", new BMIRequest());
    return "bmi2";
  }

  @PostMapping()
  public String handleBMIForm(@ModelAttribute("bmiRequest") BMIRequest request, BindingResult bindingResult, Model model) {
    if (! bindingResult.hasErrors()) {      
      BMIResult bmiResult = calculateBMI(request);
      //model.addAttribute("bmiRequest", request); 
      model.addAttribute("bmiResult", bmiResult);
    }
    return "bmi2";
  }

  //Refactor ra một hàm độc lập
  private BMIResult calculateBMI(BMIRequest request) {
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
