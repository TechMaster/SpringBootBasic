package vn.techmaster.bmi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.bmi.request.BMIRequest;
import vn.techmaster.bmi.response.BMIResult;
import vn.techmaster.bmi.service.HealthService;

@RestController
@RequestMapping("/api/bmi")
public class BMIRest {
  @Autowired
  private HealthService healthService;
  
  @PostMapping()
  public BMIResult handleBMIPost(@RequestBody BMIRequest request) {
    return healthService.calculateBMI(request);
  }
  
}
