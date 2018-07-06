package pl.krug.spring.webflux.introduction;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.krug.spring.webflux.introduction.blocking.BlockingBookRepository;
import pl.krug.spring.webflux.introduction.book.Book;
import pl.krug.spring.webflux.introduction.reactive.ReactiveBookRepository;
import pl.krug.spring.webflux.introduction.reactive.ReactiveController;

public class ReactiveControllerMockTest {

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();

  private WebTestClient client;

  @Before
  public void setup() {
    ReactiveController reactiveController = new ReactiveController(new ReactiveBookRepository(createBlockingRepository()));
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
        .contains(ApplicationConfig.NORTHAMERICA_BOOK);;
  }

  private BlockingBookRepository createBlockingRepository() {
    return new ApplicationConfig().provideBlockingRepository();
  }

}
