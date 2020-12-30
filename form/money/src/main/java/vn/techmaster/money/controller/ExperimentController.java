package vn.techmaster.money.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.money.model.Currency;
import vn.techmaster.money.request.MoneyConvertRequest;

@Controller
@RequestMapping("/exp")
public class ExperimentController {
  
  @GetMapping("/select")
  public String renderSelect(Model model) {
    model.addAttribute("moneyConvertRequest", new MoneyConvertRequest());

    ArrayList<Currency> currencies = new ArrayList<>();
    currencies.add(new Currency("USD", "US Dollar"));
    currencies.add(new Currency("THB", "Thai Bath"));
    currencies.add(new Currency("KRW", "South Korea Won"));
    currencies.add(new Currency("VND", "Vietnamese Dong"));
    model.addAttribute("currencies", currencies);  //trả về danh sách mã 3 ký tự của tiền tệ và tên tiền tệ
    return "select";    
  }

  @GetMapping("/convert")
  public String renderConvert(Model model) {
    model.addAttribute("moneyConvertRequest", new MoneyConvertRequest());

    ArrayList<Currency> currencies = new ArrayList<>();
    currencies.add(new Currency("USD", "US Dollar"));
    currencies.add(new Currency("THB", "Thai Bath"));
    currencies.add(new Currency("KRW", "South Korea Won"));
    currencies.add(new Currency("VND", "Vietnamese Dong"));
    model.addAttribute("currencies", currencies);  //trả về danh sách mã 3 ký tự của tiền tệ và tên tiền tệ
    return "convert";    
  }

}
