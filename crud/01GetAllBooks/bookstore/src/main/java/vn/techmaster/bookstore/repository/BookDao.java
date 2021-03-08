package vn.techmaster.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import vn.techmaster.bookstore.model.Book;

@Repository
public class BookDao extends Dao<Book> {

  public BookDao() {
    collections.add(new Book(1, "Không gia đình", "Chú bé Remy lang thang theo gánh xiếc của bác Vitaly"));
    collections.add(new Book(2, "Cuốn theo chiều gió", "Nội chiến Hoa kỳ, cuộc tình giữa Red Butler và Ohara"));
  }

  @Override
  public List<Book> getAll() {
    return collections;
  }

  @Override
  public Optional<Book> get(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void add(Book t) {
    // TODO Auto-generated method stub
  }

  @Override
  public void update(Book t) {
    // TODO Auto-generated method stub
  }

  @Override
  public void deleteByID(int id) {
    // TODO Auto-generated method stub
  }

  @Override
  public void delete(Book t) {
    // TODO Auto-generated method stub
  }  
}