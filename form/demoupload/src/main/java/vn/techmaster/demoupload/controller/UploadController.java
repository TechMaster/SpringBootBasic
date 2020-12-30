package vn.techmaster.demoupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.demoupload.entity.Person;
import vn.techmaster.demoupload.exception.StorageException;
import vn.techmaster.demoupload.service.StorageService;

@Controller
public class UploadController {
  @Autowired
  private StorageService storageService;
  
  @GetMapping("/")
  public String home() {
    return "index";
  }

  @GetMapping("/success")
  public String success() {
    return "success";
  }

  @GetMapping("/failure")
  public String failure() {
    return "failure";
  }

  @PostMapping(value = "/doUpload", consumes = { "multipart/form-data" })
  public String upload(@ModelAttribute Person person, ModelMap modelMap, Model model) {

    modelMap.addAttribute("person",person);

    storageService.uploadFile(person.getPhoto());
    model.addAttribute("name", person.getName());   
    model.addAttribute("photo", person.getPhoto().getOriginalFilename());
    return "success";
  }


  @ExceptionHandler(StorageException.class)
  public String handleStorageFileNotFound(StorageException e, Model model) {
    model.addAttribute("errorMessage", e.getMessage());
    return "failure";
  }
}