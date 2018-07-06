package pl.krug.spring.webflux.introduction.blocking;

import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krug.spring.webflux.introduction.book.Author;
import pl.krug.spring.webflux.introduction.book.Book;

@RestController
@RequestMapping(value = "/blocking")
public class BlockingController {

  private final BlockingBookRepository bookRepository;

  @Inject
  BlockingController(BlockingBookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping("/books")
  public List<Book> books() {
    return bookRepository.findAll();
  }

  @GetMapping("/books/{isbn}")
  public Book book(@PathVariable String isbn) {
    return bookRepository.getById(isbn);
  }

  @GetMapping(value = "/books", params = {"firstName", "lastName"})
  public List<Book> booksByAuthor(@RequestParam String firstName, @RequestParam String lastName) {
    return bookRepository.findByAuthor(Author.of(firstName, lastName));
  }

}
