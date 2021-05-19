package vn.techmaster.pathquery.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

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
public class PathControllerWithValidation {
  @GetMapping("/api/foods-validation")
  public String getFood(//@Valid (Nếu ko dùng @Validated thì dùng @Valid ở đây)
    @RequestParam @Positive(message = "food id phải lơn hơn 0") int id, 
    @RequestParam @NotBlank String type, @RequestParam(required = false) String description) {
    return "Food ID: " + id + ", type: " + type + ", description: " + (description == null ? "No description" : description);
  }

  @GetMapping("/city-validation/id/{cityId}")
  public String getCityID(@PathVariable @Min(5) int cityId) {
    return "City id: " + cityId;
  }

  @PostMapping("/api/foods-validation")
  public ResponseEntity<Food> createFood(@Valid @RequestBody Food food) {
    return ResponseEntity.ok(new Food(10, food.getName(), food.getType(), food.getDescription()));
  }
}
