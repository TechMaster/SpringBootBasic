package com.example.demo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {
    @GetMapping("/message1")
    public String messageShow(Model model) {
        return "message1";
    }

    @GetMapping("/message2")
    public String messageShow2(Model model) {
        model.addAttribute("userName", "Thinh");
        model.addAttribute("country", "Vietnam");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        model.addAttribute("todayDate", dateFormat.format(cal.getTime()));
        
        return "message2";
    }

    @GetMapping("/message3")
    public String messageShow2(HttpSession httpSession, Map<String, Object> model) {
        model.put("userName", "Thinh");
        model.put("country", "Vietnam");
        model.put("todayDate", LocalDate.now());
        model.put("today", new Date());
        httpSession.setAttribute("userName", "HCM");
        
        return "message2";
    }

    @GetMapping("/localVal")
    public String localVal(Model model, HttpServletRequest request) {
        String[] flowers = new String[] {"Rose","Lily", "Tulip", "Carnation", "Hyacinth" };
        model.addAttribute("flowers", flowers);
        return "localVal";
    }

    @GetMapping("/localVal2")
    public String localVal2(Model model, HttpServletRequest request) {
        String[] flowers = new String[] {"Rose","Lily", "Tulip", "Carnation", "Hyacinth" };
        model.addAttribute("flowers", flowers);;
        return "localVal2";
    }

    @GetMapping("/asteriskVariable")
    public String asteriskVariable(HttpSession httpSession) {
        httpSession.setAttribute("user", new User("Thinh", "Tran", "Vietnam"));
        return "asteriskVariable";
    }

    @GetMapping("/elvisoperator")
    public String elvisoperator(HttpSession httpSession) {
        httpSession.setAttribute("user", new User("Thinh", "Tran", null));
        return "elvisoperator";
    }
}
