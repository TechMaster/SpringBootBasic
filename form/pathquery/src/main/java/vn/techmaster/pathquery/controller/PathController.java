package vn.techmaster.pathquery.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PathController {
  //http://localhost:8080/city/hanoi
  @GetMapping("/city/{cityName}")
  @ResponseBody
  public String getCityName(@PathVariable String cityName) {
    return "City name: " + cityName;
  }

  //http://localhost:8080/city/id/1
  @GetMapping("/city/id/{cityId}")
  @ResponseBody
  public String getCityID(@PathVariable int cityId) {
    return "City id: " + cityId;
  }

  //http://localhost:8080/food?id=abc
  @GetMapping("/food")
  @ResponseBody
  public String getFoodByIdQueryParam(@RequestParam String id) {
    return "Food ID: " + id;
  }

  //http://localhost:8080/foods?id=abc&type=mexico
  @GetMapping("/foods")  //Nếu đặt trùng @GetMapping("/food") sẽ báo lỗi khi biên dịch
  @ResponseBody
  public String getFoodByIdAndType(@RequestParam(name = "id") String foodId, @RequestParam String type) {
    return "Food ID: " + foodId + ", type: " + type;
  }

  //http://localhost:8080//api/foos?id=abc&type=mexico
  @GetMapping("/api/foos")
  @ResponseBody
  public String getFoos(@RequestParam Map<String,String> allParams) {
    return "Parameters are " + allParams.entrySet();
  }
}
