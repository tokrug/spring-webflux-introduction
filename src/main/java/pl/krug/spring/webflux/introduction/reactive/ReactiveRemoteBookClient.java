package pl.krug.spring.webflux.introduction.reactive;

import javax.inject.Inject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import pl.krug.spring.webflux.introduction.book.Author;
import pl.krug.spring.webflux.introduction.book.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveRemoteBookClient {

  private final WebClient webClient;

  @Inject
  ReactiveRemoteBookClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public static ReactiveRemoteBookClient create() {
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:2424")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        .build();
    return new ReactiveRemoteBookClient(webClient);
  }

  public Mono<Book> getById(String isbn) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path("/books/{isbn}").build(isbn))
        .retrieve()
        .bodyToMono(Book.class);
  }

  public Flux<Book> findByAuthor(Author author) {
    return webClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/books")
            .queryParam("firstName", author.getFirstName())
            .queryParam("lastName", author.getLastName())
            .build())
        .exchange()
        .flatMapMany(response -> response.bodyToFlux(Book.class));
  }

  public Flux<Book> findAll() {
    return webClient
        .get()
        .uri("/books")
        .retrieve()
        .bodyToFlux(Book.class);
  }

  public Mono<Void> save(Book book) {
    return webClient
        .post()
        .uri("/books")
        .syncBody(book)
        .retrieve()
        .bodyToMono(Void.class);
  }

}
