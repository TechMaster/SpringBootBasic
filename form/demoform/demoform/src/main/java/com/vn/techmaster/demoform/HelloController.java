package com.vn.techmaster.demoform;

import com.vn.techmaster.demoform.model.Greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String helloForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "helloform";
    }

    @PostMapping
    public String helloFormSubmit(@ModelAttribute("greeting") Greeting greetingForm, Model model) {
        return "helloresult";
    }
}
