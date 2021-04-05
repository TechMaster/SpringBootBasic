package vn.techmaster.iot.service;

import java.util.Random;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import vn.techmaster.iot.controller.reponse.IOTMeasure;

@Service
@EnableScheduling
public class IOTService {
  private float temperature;
  private float moisture;

  public float measureTemperature() {
    float lowTemp = 10F;
    float highTemp = 40F;
    float newTemp = temperature + (new Random().nextFloat() - 0.5F) * 5F;

    newTemp = newTemp < lowTemp ? lowTemp : newTemp;
    newTemp = newTemp > highTemp ? highTemp: newTemp;
    return newTemp;
  }

  public float measureMoisture() {
    float lowMoisture = 30F;
    float highMoisture = 100F;
    float newMoisture = moisture + (new Random().nextFloat() - 0.5F) * 10F;

    newMoisture = newMoisture < lowMoisture ? lowMoisture : newMoisture;
    newMoisture = newMoisture > highMoisture ? highMoisture: newMoisture ;
    return newMoisture;

  }
  @Scheduled(fixedRate = 1000)
  public void collectMeasureDataAtFixedRate() throws InterruptedException {
    temperature = measureTemperature();
    moisture = measureMoisture();
  }

  public IOTMeasure getData() throws InterruptedException {
    Thread.sleep(10);
    return new IOTMeasure(temperature, moisture);
  }
}
