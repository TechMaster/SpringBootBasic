package vn.techmaster.bmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import vn.techmaster.bmi.service.MoneyConverter;




@Controller
public class MoneyController {

  @Autowired
  private MoneyConverter moneyConverter;

  
}
