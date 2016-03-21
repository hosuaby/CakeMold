package io.hosuaby.cakemold.pojos;

/**
 * Example of trivial class that respects Java Bean convention.
 *
 * @author Alexei KLENIN
 */
public class Person {

  private String firstName;
  private String lastName;
  private String email;
  private int age;
  private boolean happiness;
  private String favoriteToy;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isHappiness() {
    return happiness;
  }

  /**
   * Only yourself can make you happy!
   *
   * @param happiness happiness
   */
  private void setHappiness(boolean happiness) {
    this.happiness = happiness;
  }

  public String getFavoriteToy() {
    return favoriteToy;
  }

  public void setFavoriteToy(String favoriteToy) {
    throw new NullPointerException();
  }
}
