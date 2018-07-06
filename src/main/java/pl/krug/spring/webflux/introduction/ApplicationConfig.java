package pl.krug.spring.webflux.introduction;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.EnableWebFlux;
import pl.krug.spring.webflux.introduction.blocking.BlockingBookRepository;
import pl.krug.spring.webflux.introduction.book.Author;
import pl.krug.spring.webflux.introduction.book.Book;
import pl.krug.spring.webflux.introduction.reactive.ReactiveRemoteBookClient;
import pl.krug.spring.webflux.introduction.reactive.router.RouterConfiguration;

@SpringBootApplication
@EnableWebFlux
@Import(RouterConfiguration.class)
public class ApplicationConfig {

  public static final Book KING_BOOK = Book.of(
      "978-1-891830-65-5", "The King",
      Author.of("Rich", "Koslowski"));
  public static final Book NORTHAMERICA_BOOK = Book.of(
      "978-1772130003", "Mushrooms of Northeast North America",
      Author.of("George", "Barron"));
  public static final Book WILD_MUSHROOM_BOOK = Book.of(
      "978-0295964805", "The New Savory Wild Mushroom",
      Author.of("Margaret", "McKenny"));

  @Bean
  BlockingBookRepository provideBlockingRepository() {
    List<Book> books = new ArrayList<>();
    books.add(KING_BOOK);
    books.add(NORTHAMERICA_BOOK);
    books.add(WILD_MUSHROOM_BOOK);
    return BlockingBookRepository.of(books);
  }

  @Bean
  ReactiveRemoteBookClient provideRemoteClient() {
    return ReactiveRemoteBookClient.create();
  }

}
