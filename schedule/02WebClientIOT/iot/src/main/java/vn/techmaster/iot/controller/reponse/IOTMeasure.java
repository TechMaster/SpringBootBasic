package vn.techmaster.iot.controller.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IOTMeasure {
  private float temperature;
  private float moisture;
}