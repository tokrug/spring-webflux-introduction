package pl.krug.spring.webflux.introduction;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import pl.krug.spring.webflux.introduction.blocking.BlockingBookRepository;
import pl.krug.spring.webflux.introduction.book.Book;
import pl.krug.spring.webflux.introduction.reactive.router.BookHandler;
import pl.krug.spring.webflux.introduction.reactive.router.RouterConfiguration;

public class RouterTest {

  private WebTestClient client;

  @Before
  public void setup() {
    RouterConfiguration routerConfiguration = new RouterConfiguration();
    RouterFunction routerFunction = routerConfiguration
        .bookRouter(new BookHandler(createBlockingRepository()));
    client = WebTestClient.bindToRouterFunction(routerFunction).build();
  }

  @Test
  public void simpleTest() {
    client.get()
        .uri("/router/books")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Book.class).hasSize(3)
        .contains(ApplicationConfig.WILD_MUSHROOM_BOOK);;
  }

  private BlockingBookRepository createBlockingRepository() {
    return new ApplicationConfig().provideBlockingRepository();
  }

}
