package io.hosuaby.cakemold;

/**
 * Main class with public static methods.
 *
 * @author Alexei KLENIN
 */
public class CakeMold {

  /**
   * Returns builder for instance of specified class.
   *
   * @param clazz class of POJO to build
   * @param <T> type of POJO
   * @return builder of instance
   */
  public static <T> PojoBuilder<T> of(Class<T> clazz) {
    return new PojoBuilder<>(clazz);
  }

}
