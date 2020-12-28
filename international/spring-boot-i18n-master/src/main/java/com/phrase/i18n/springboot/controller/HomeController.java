package com.phrase.i18n.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
