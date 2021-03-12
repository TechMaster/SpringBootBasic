package vn.techmaster.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.BookPOJO;
import vn.techmaster.bookstore.repository.BookRespository;

@Service
public class BookService implements IBookService {
  @Autowired
  private BookRespository bookRepo;

  @Override
  public List<Book> findAll() {
    return bookRepo.findAll();
  }

  @Override
  public Optional<Book> findById(Long id) {    
    return bookRepo.findById(id);
  }

  @Override
  public Book save(BookPOJO book) {
    Book newBook = new Book(book.getTitle(), book.getAuthor());
    return bookRepo.save(newBook);
  }

  @Override
  public void update(long id, BookPOJO book) {
    Book updatedBook = new Book(id, book.getTitle(), book.getAuthor());
    Optional<Book> optionalBook = bookRepo.findById(id);
    if (optionalBook.isPresent()) {
      bookRepo.save(updatedBook);
    } else {
      throw new ResourceNotFoundException();
    }
  }

  @Override
  public void updateTitle(long id, String title) {
    Optional<Book> optionalBook = bookRepo.findById(id);
    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      book.setTitle(title);
      bookRepo.save(book);
    } else {
      throw new ResourceNotFoundException();
    }
  }

  @Override
  public void deleteById(long id) {
    Optional<Book> optionalBook = bookRepo.findById(id);
    if (optionalBook.isPresent()) {      
      bookRepo.delete(optionalBook.get());
    } else {
      throw new ResourceNotFoundException();
    }
  }  
  
}
