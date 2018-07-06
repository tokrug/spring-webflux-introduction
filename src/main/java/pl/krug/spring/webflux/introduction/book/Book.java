package pl.krug.spring.webflux.introduction.book;

import java.util.Objects;

public class Book {

  private String isbn;
  private String title;
  private Author author;

  Book() {}

  Book(String isbn, String title, Author author) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
  }

  public static Book of(String isbn, String title, Author author) {
    return new Book(isbn, title, author);
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public Author getAuthor() {
    return author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(isbn, book.isbn) &&
        Objects.equals(title, book.title) &&
        Objects.equals(author, book.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn, title, author);
  }
}
