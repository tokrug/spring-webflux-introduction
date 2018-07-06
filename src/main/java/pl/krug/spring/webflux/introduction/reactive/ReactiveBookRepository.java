package pl.krug.spring.webflux.introduction.reactive;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import pl.krug.spring.webflux.introduction.blocking.BlockingBookRepository;
import pl.krug.spring.webflux.introduction.book.Author;
import pl.krug.spring.webflux.introduction.book.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactiveBookRepository {

  private final BlockingBookRepository blockingBookRepository;

  @Inject
  public ReactiveBookRepository(BlockingBookRepository blockingBookRepository) {
    this.blockingBookRepository = blockingBookRepository;
  }

  public Mono<Book> getById(String isbn) {
    return Mono.just(isbn)
        .map(blockingBookRepository::getById);
  }

  public Flux<Book> findByAuthor(Author author) {
    return Mono.just(author)
        .flatMapMany((auth) -> Flux.fromStream(blockingBookRepository.findByAuthor(auth).stream()));
  }

  public Flux<Book> findAll() {
    return Flux.fromStream(() -> blockingBookRepository.findAll().stream());
  }

  public Mono<Void> save(Book book) {
    return Mono.fromRunnable(() -> blockingBookRepository.save(book));
  }

}
