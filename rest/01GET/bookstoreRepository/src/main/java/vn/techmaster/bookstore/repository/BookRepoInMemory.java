package vn.techmaster.bookstore.repository;

import java.util.ArrayList;
import java.util.List;

import vn.techmaster.bookstore.model.Book;

public class BookRepoInMemory implements IBookRepo {
	private List<Book> books;

	BookRepoInMemory() {
		books = new ArrayList<>();
		books.add(new Book(1, "Good with the wind", "gone_with_the_wind.jpg"));
		books.add(new Book(2, "Nhà Giả Kim Thuật", "gia_kim_thuat.jpg"));
		books.add(new Book(3, "Harry Porter", "harry_porter.jpg"));
		books.add(new Book(4, "Deep Work", "deep_work.jpg"));
	}

	@Override
	public List<Book> getAll() {
		return books;
	}
}
