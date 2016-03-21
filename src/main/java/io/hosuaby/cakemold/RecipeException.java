package io.hosuaby.cakemold;

/**
 * Runtime exception with user friendly message wrapping exception th
 * rowed by
 * reflection tools.
 *
 * @author Alexei KLENIN
 */
public class RecipeException extends RuntimeException {

  private static final String TEMPLATE = "Oops ! %s ! :(";

  /* Object instantiation exceptions */
  static final String NO_PUBLIC_NULLARY_CONSTRUCTOR = String.format(
      TEMPLATE, "Class %s doesn't have public constructor with no arguments");
  static final String ABSTRACT_CLASS_INSTANTIATION = String.format(
      TEMPLATE, "You actually tries to build POJO from abstract class %s");
  static final String EXCEPTION_IN_CONSTRUCTOR = String.format(
      TEMPLATE, "An exception occurred in constructor of class %s");

  /* Setter invocation exceptions */
  static final String NO_PUBLIC_SETTER = String.format(
      TEMPLATE, "No public setter %s found");
  static final String EXCEPTION_IN_SETTER = String.format(
      TEMPLATE, "An exception occurred in setter %s");

  private RecipeException(String message) {
    super(message);
  }

  public RecipeException(String message, Throwable cause) {
    super(message, cause);
  }

  public static RecipeException noPublicNullaryConstructor(
      final Class<?> clazz) {
    return new RecipeException(String.format(
        NO_PUBLIC_NULLARY_CONSTRUCTOR, clazz.getName()));
  }

  public static RecipeException abstractClassInstantiation(
      final Class<?> clazz) {
    return new RecipeException(String.format(
        ABSTRACT_CLASS_INSTANTIATION, clazz.getName()));
  }

  public static RecipeException exceptionInConstructor(final Class<?> clazz,
      final Throwable cause) {
    return new RecipeException(String.format(EXCEPTION_IN_CONSTRUCTOR,
        clazz.getName()), cause);
  }

  public static RecipeException noPublicSetter(final String methodName) {
    return new RecipeException(String.format(NO_PUBLIC_SETTER, methodName));
  }

  public static RecipeException exceptionInSetter(final String methodName,
      final Throwable cause) {
    return new RecipeException(String.format(EXCEPTION_IN_SETTER, methodName),
        cause);
  }

}
