package pl.krug.spring.webflux.introduction;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.krug.spring.webflux.introduction.book.Book;
import pl.krug.spring.webflux.introduction.reactive.ReactiveController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReactiveControllerContextTest {

  @Inject
  private ReactiveController reactiveController;

  private WebTestClient client;

  @Before
  public void setup() {
    client = WebTestClient.bindToController(reactiveController).build();
  }

  @Test
  public void simpleTest() {
    client
        .get()
        .uri("/reactive/books")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Book.class).hasSize(3)
        .contains(ApplicationConfig.KING_BOOK);
  }

}
