package pl.krug.spring.webflux.introduction.reactive.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class RouterConfiguration {

  @Bean
  public RouterFunction bookRouter(BookHandler handler) {
    return route(GET("/router/books").and(accept(APPLICATION_JSON)), handler::getBooks);
  }

}
