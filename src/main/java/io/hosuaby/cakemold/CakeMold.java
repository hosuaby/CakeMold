package io.hosuaby.cakemold;

/**
 * {@code CakeMold} helps you to creates your POJOs with fluent builder. It uses
 * reflexion to find setters on the classes.
 * <br>
 * All Java reflexion related exceptions are translated to friendly runtime
 * {@link RecipeException} to avoid to pollute your code with ennoying
 * exception handlers.
 * <br>
 * Start to build immediately with :
 * <pre>
 * {@code
 *   MyClass myPojo = CakeMold.of(MyClass.class)..
 * }
 * </pre>
 *
 * @author Alexei KLENIN
 */
public class CakeMold {

  private CakeMold() {
  }

  /**
   * Creates builder for your class. Built class must respect JavaBean
   * convention. It means :
   * <ol>
   *   <li>have a public default constructor with no arguments</li>
   *   <li>mutator for attribute must be composed from word "set" concatenated
   *       to name of attributed with upper-cased first letter</li>
   * </ol>
   * Start to build immediately with :
   * <pre>
   * {@code
   *   MyClass myPojo = CakeMold.of(MyClass.class)..
   * }
   * </pre>
   *
   * @param clazz class of POJO to build
   * @param <T> type of POJO
   * @return builder of instance
   */
  public static <T> PojoBuilder<T> of(Class<T> clazz) {
    return new PojoBuilder<>(clazz);
  }

}
