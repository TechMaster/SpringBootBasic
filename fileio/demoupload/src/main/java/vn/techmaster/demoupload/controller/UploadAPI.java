package vn.techmaster.demoupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.demoupload.entity.Person;
import vn.techmaster.demoupload.service.StorageService;

@RestController
@RequestMapping("/api")
public class UploadAPI {
  @Autowired
  private StorageService storageService;

  @PostMapping(value = "/upload", consumes = { "multipart/form-data" })
  public String upload(@ModelAttribute Person person,  Model model) {
    storageService.uploadFile(person.getPhoto());    
    return person.getName() + " : " + person.getEmail();
  }
}