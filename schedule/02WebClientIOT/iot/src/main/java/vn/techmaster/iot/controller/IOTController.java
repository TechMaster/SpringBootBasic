package vn.techmaster.iot.controller;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import vn.techmaster.iot.controller.reponse.IOTMeasure;
import vn.techmaster.iot.service.IOTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class IOTController {

  @Autowired private IOTService iotService;
  
  @GetMapping("/iot")
  public IOTMeasure getIOTMeasure() throws InterruptedException {
    return iotService.getData();
  }

  @GetMapping("/mono")
  public Mono<IOTMeasure> getIOTMeasureMono() throws InterruptedException {
    return Mono.just(iotService.getData());
  }
  
}
