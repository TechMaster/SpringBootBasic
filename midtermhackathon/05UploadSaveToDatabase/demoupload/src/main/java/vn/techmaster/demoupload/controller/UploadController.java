package vn.techmaster.demoupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.demoupload.controller.request.BugRequest;
import vn.techmaster.demoupload.exception.StorageException;
import vn.techmaster.demoupload.service.IBugService;

@Controller
public class UploadController {
  @Autowired
  private IBugService bugService;

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("bugRequest", new BugRequest());
    return "index";
  }

  @PostMapping(value = "/bug", consumes = { "multipart/form-data" })
  public String upload(@ModelAttribute BugRequest bugRequest, Model model) {
    bugService.createNewBug(bugRequest);
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