package pl.krug.spring.webflux.introduction.blocking;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;
import pl.krug.spring.webflux.introduction.book.Author;
import pl.krug.spring.webflux.introduction.book.Book;
import pl.krug.spring.webflux.introduction.book.BookNotPresentException;

public class BlockingBookRepository {

  private final List<Book> books;

  BlockingBookRepository(List<Book> books) {
    this.books = books;
  }

  public static BlockingBookRepository of(List<Book> books) {
    return new BlockingBookRepository(books);
  }

  public Book getById(String isbn) {
    return books.stream()
        .filter(book -> book.getIsbn().equals(isbn))
        .findAny()
        .orElseThrow(() -> new BookNotPresentException(String.format("Book with ISBN %s has not been found", isbn)));
  }

  public List<Book> findByAuthor(Author author) {
    return books.stream()
        .filter(book -> book.getAuthor().equals(author))
        .collect(Collectors.toList());
  }

  public List<Book> findAll() {
    return ImmutableList.copyOf(books);
  }

  public void save(Book book) {
    books.add(book);
  }

}
