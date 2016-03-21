package io.hosuaby.cakemold.pojos;

/**
 * Example of class that throws an exception in constructor.
 *
 * @author Alexei KLENIN
 */
public class Fool {

  private String favoriteToy;

  public Fool() {
    throw new NullPointerException();
  }

  public String getFavoriteToy() {
    return favoriteToy;
  }

  public void setFavoriteToy(String favoriteToy) {
    this.favoriteToy = favoriteToy;
  }
}
