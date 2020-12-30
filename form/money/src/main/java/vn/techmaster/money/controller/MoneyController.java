package vn.techmaster.money.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.money.model.CurrencyRate;
import vn.techmaster.money.request.MoneyConvertRequest;
import vn.techmaster.money.service.MoneyConverter;


@Controller
@RequestMapping("/money")
public class MoneyController {

  @Autowired
  private MoneyConverter moneyConverter;

  @GetMapping()
  public String renderForm(Model model) {
    model.addAttribute("moneyConvertRequest", new MoneyConvertRequest());

    List<CurrencyRate> currencies = moneyConverter.getSortedCurrencyCode();
    
    model.addAttribute("currencies", currencies);  
    return "form";
  }
  
}
