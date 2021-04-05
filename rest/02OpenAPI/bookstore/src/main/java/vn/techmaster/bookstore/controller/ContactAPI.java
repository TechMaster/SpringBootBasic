package vn.techmaster.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import vn.techmaster.bookstore.model.Contact;
import vn.techmaster.bookstore.repository.ContactRepository;

@RestController
@RequestMapping(value = "/api/contacts")
public class ContactAPI {
  @Autowired
  private ContactRepository contactRepository;

  @Operation(summary = "Get all contacts")
  @GetMapping(value = "/")
  public ResponseEntity<List<Contact>> findAllContacts() {
     List<Contact> contacts = contactRepository.findAll();
    return ResponseEntity.ok(contacts);
  }
}
