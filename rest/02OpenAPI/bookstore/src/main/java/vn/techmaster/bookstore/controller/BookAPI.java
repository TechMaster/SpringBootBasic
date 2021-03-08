package vn.techmaster.bookstore.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.BookPOJO;
import vn.techmaster.bookstore.service.IBookService;

@RestController
@RequestMapping(value = "/api/books")
public class BookAPI {
  @Autowired
  private IBookService bookService;

  @Operation(summary = "Get all books")
  @GetMapping(value = "/")
  public ResponseEntity<List<Book>> findAllBooks() {
    List<Book> books = bookService.findAll();
    return ResponseEntity.ok(books);
  }

  @GetMapping("/{bookId}")
  @Operation(summary = "Get a book by id")
  public ResponseEntity<Book> findBookById(
    @Parameter(description = "id of book to be searched") 
    @PathVariable long bookId) {
    Optional<Book> optionalBook = bookService.findById(bookId);
    if (optionalBook.isPresent()) {
      return ResponseEntity.ok(optionalBook.get()); // return 200, with json body
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
    }
  }

  @PostMapping("/")
  @Operation(summary = "Create a new book")
  public ResponseEntity<Book> addBook(@Parameter(description = "book to be created") @RequestBody BookPOJO book) {
    Book newBook = bookService.save(book);
    try {
      return ResponseEntity.created(new URI("/api/books/" + newBook.getId())).body(newBook);
    } catch (URISyntaxException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PutMapping("/{bookId}")
  @Operation(summary = "Update a book by id", parameters = {
      @Parameter(name = "bookId", in = ParameterIn.QUERY, required = true, description = "id của sách") })
  public ResponseEntity<Void> updateBook(@RequestBody BookPOJO book, @PathVariable long bookId) {
    try {
      bookService.update(bookId, book);
      return ResponseEntity.ok().build();
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/{bookId}")
  public ResponseEntity<Void> updateBookTitle(@RequestBody String title, @PathVariable long bookId) {
    try {
      bookService.updateTitle(bookId, title);
      return ResponseEntity.ok().build();
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping(path = "/{bookId}")
  public ResponseEntity<Void> deleteBookById(@PathVariable long bookId) {
    try {
      bookService.deleteById(bookId);
      return ResponseEntity.ok().build();
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Beta API method, đang kiểm thử", hidden = true)
  @GetMapping(value = "/top5")
  public ResponseEntity<List<Book>> getTop5Books() {
    List<Book> books = bookService.findAll();
    List<Book> top5books = books.stream().limit(5).collect(Collectors.toList());
    return ResponseEntity.ok(top5books);
  }
}
