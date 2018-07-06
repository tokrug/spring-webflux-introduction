package pl.krug.spring.webflux.introduction.book;

public class BookNotPresentException extends RuntimeException {

  public BookNotPresentException() {
  }

  public BookNotPresentException(String message) {
    super(message);
  }

  public BookNotPresentException(String message, Throwable cause) {
    super(message, cause);
  }

  public BookNotPresentException(Throwable cause) {
    super(cause);
  }

}
