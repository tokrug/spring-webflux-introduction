package pl.krug.spring.webflux.introduction.reactive.file;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.BaseStream;
import reactor.core.publisher.Flux;

public class FileReader {

  public Flux<String> readLines(String path) {
    return Flux.using(() -> Files.lines(Paths.get(path)),
        Flux::fromStream,
        BaseStream::close
    );
  }

}
