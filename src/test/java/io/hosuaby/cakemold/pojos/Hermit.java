package io.hosuaby.cakemold.pojos;

/**
 * Example of class that has no public nullable constructor.
 *
 * @author Alexei KLENIN
 */
public class Hermit {

  private String nickname;

  private Hermit() {
    this.nickname = "Reclusive hermit";
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
