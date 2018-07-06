package pl.krug.spring.webflux.introduction.stream;

import java.util.stream.IntStream;

public class StreamExample {

  public int sumOfDoubledOddNumbers(int start, int end) {
    return IntStream.rangeClosed(start, end)
        .filter(this::isOdd)
        .map(this::doubleIt)
        .sum();
  }

  private boolean isOdd(int number) {
    return number % 2 == 1;
  }

  private int doubleIt(int number) {
    return number * 2;
  }

}
