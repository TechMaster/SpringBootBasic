package vn.techmaster.pathquery.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.pathquery.Food;

@RestController
@Validated
public class PathController {
  //http://localhost:8080/city/hanoi
  @GetMapping("/city/{cityName}")
  public String getCityName(@PathVariable String cityName) {
    return "City name: " + cityName;
  }

  //http://localhost:8080/city/id/1
  @GetMapping("/city/id/{cityId}")
  public String getCityID(@PathVariable int cityId) {
    return "City id: " + cityId;
  }

    //http://localhost:8080/city/vietnam/hanoi
    @GetMapping("/city/{countryName}/{cityName}")
    public String getCity(@PathVariable(required=false) String countryName, @PathVariable String cityName) {
      return "City name: " + cityName + (countryName == null ? "" : " of country: " + countryName);
    }

  //http://localhost:8080/food?id=abc
  @GetMapping("/food")
  public String getFoodByIdQueryParam(@RequestParam String id) {
    return "Food ID: " + id;
  }

  //http://localhost:8080/foods?id=abc&type=mexico
  @GetMapping("/foods")  //Nếu đặt trùng @GetMapping("/food") sẽ báo lỗi khi biên dịch
  public String getFoodByIdAndType(@RequestParam(name = "id") String foodId, @RequestParam String type) {
    return "Food ID: " + foodId + ", type: " + type;
  }

  //http://localhost:8080/foods-plus?id=abc&type=mexico
  @GetMapping("/foods-plus")
  public String getFoodByIdAndTypeAndDescription(@RequestParam(name = "id") String foodId, 
    @RequestParam String type, @RequestParam(required = false) String description) {
    return "Food ID: " + foodId + ", type: " + type + ", description: " + (description == null ? "No description" : description);
  }

  //http://localhost:8080//api/foos?id=abc&type=mexico
  @GetMapping("/api/foos")
  public String getFoos(@RequestParam Map<String,String> allParams) {
    return "Parameters are " + allParams.entrySet();
  }

  //http://localhost:8080//api/foods/ (Method=POST) (Use postman to run)
  @PostMapping("/api/foods")
  public ResponseEntity<Food> createFood(@RequestBody Food food) {
    return ResponseEntity.ok(new Food(10, food.getName(), food.getType(), food.getDescription()));
  }
}
