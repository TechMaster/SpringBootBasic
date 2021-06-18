package com.techmaster.employeemanagement.controller;

import java.util.List;

import javax.validation.Valid;

import com.techmaster.employeemanagement.exception.ResourceNotFoundException;
import com.techmaster.employeemanagement.model.Employee;
import com.techmaster.employeemanagement.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ExceptionHandler(ResourceNotFoundException.class)
    public String home(Model model, Exception ex) {
        List<Employee> employees = this.employeeService.findAll();
        model.addAttribute("ex", ex.getMessage());
        model.addAttribute("employees", employees);
        return "home";

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", "Add New Employee");
        return "form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("title") String title, @ModelAttribute @Valid Employee employee, 
        BindingResult bindingResult, RedirectAttributes rAttributes, Model model) {
        model.addAttribute("title", title);
        if (bindingResult.hasErrors()) {
            return "form";
        }
        this.employeeService.addOrUpdate(employee);
        rAttributes.addFlashAttribute("successMsg", "Employee duoc update thanh cong");
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Employee employee = this.employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("title", "Edit Employee");
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id, RedirectAttributes rAttributes, Model model) {
        this.employeeService.deleteById(id);
        rAttributes.addFlashAttribute("successMsg", "Employee duoc delete thanh cong");
        return "redirect:/employee";
    }

}
