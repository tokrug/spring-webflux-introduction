package pl.krug.spring.webflux.introduction.book;

import java.util.Objects;

public class Author {

  private String firstName;
  private String lastName;

  Author() {}

  Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public static Author of(String firstName, String lastName) {
    return new Author(firstName, lastName);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return Objects.equals(firstName, author.firstName) &&
        Objects.equals(lastName, author.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}
