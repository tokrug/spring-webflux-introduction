package pl.krug.spring.webflux.introduction.reactive.router;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.krug.spring.webflux.introduction.blocking.BlockingBookRepository;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {

  private final BlockingBookRepository blockingBookRepository;

  @Inject
  public BookHandler(BlockingBookRepository blockingBookRepository) {
    this.blockingBookRepository = blockingBookRepository;
  }

  public Mono<ServerResponse> getBooks(ServerRequest serverRequest) {
    return ServerResponse.ok().syncBody(blockingBookRepository.findAll());
  }

}
