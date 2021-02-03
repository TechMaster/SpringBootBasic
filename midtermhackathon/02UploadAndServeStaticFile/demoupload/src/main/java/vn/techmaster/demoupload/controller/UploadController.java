package vn.techmaster.demoupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.demoupload.controller.request.PersonRequest;
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

  @PostMapping(value = "/doUpload", consumes = { "multipart/form-data" })
  public String upload(@ModelAttribute PersonRequest person, Model model) {
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

  @GetMapping("/success")
  public String success() {
    return "success";
  }

  @GetMapping("/failure")
  public String failure() {
    return "failure";
  }
}