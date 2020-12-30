package vn.techmaster.money.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import vn.techmaster.money.service.MoneyConverter;




@Controller
public class MoneyController {

  @Autowired
  private MoneyConverter moneyConverter;

  
}
