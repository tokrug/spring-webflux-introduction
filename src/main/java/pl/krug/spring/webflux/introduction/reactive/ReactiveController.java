package pl.krug.spring.webflux.introduction.reactive;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.krug.spring.webflux.introduction.book.Author;
import pl.krug.spring.webflux.introduction.book.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/reactive")
public class ReactiveController {

  private final ReactiveBookRepository bookRepository;

  @Inject
  public ReactiveController(ReactiveBookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping("/books")
  public Flux<Book> books() {
    return bookRepository.findAll();
  }

  @GetMapping("/books/{isbn}")
  public Mono<Book> book(@PathVariable String isbn) {
    return bookRepository.getById(isbn);
  }

  @GetMapping(value = "/books", params = {"firstName", "lastName"})
  public Flux<Book> booksByAuthor(@RequestParam String firstName, @RequestParam String lastName) {
    return bookRepository.findByAuthor(Author.of(firstName, lastName));
  }

}
