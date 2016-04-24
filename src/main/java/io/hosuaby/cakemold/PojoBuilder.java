package io.hosuaby.cakemold;

import static io.hosuaby.cakemold.Utils.getSetterMethod;
import static io.hosuaby.cakemold.Utils.ucfirst;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Fluent POJO builder using reflexion.
 *
 * @param <T> type of built POJO
 *
 * @author Alexei KLENIN
 */
public class PojoBuilder<T> {

  private final Class<T> clazz;
  private final T instance;

  /**
   * Constructor.
   *
   * @param clazz class of built POJO
   */
  protected PojoBuilder(final Class<T> clazz) {
    this.clazz = clazz;
    T instance = null;

    try {
      instance = clazz.getConstructor().newInstance();
    } catch (NoSuchMethodException | IllegalAccessException exception) {
      throw RecipeException.noPublicNullaryConstructor(clazz);
    } catch (InstantiationException exception) {
      throw RecipeException.abstractClassInstantiation(clazz);
    } catch (InvocationTargetException exception) {
      throw RecipeException.exceptionInConstructor(clazz, exception);
    } finally {
      this.instance = instance;
    }
  }

  /**
   * Initializes attributes of POJO via its mutator. Returns current builder for
   * fluent usage.
   * <br><br>
   * Suppose, we have class {@code Titi} with attribute {@code toto} and mutator
   * for this attribute {@code setToto}. We can set this attribute with
   * following :
   * <br>
   * <pre>
   * {@code
   *    Titi titi = CakeMold.of(Titi.class).set("toto", "bla") ...
   * }
   * </pre>
   *
   * @param attributeName name of the attribute to set
   * @param value value to set
   * @return this builder
   */
  public PojoBuilder<T> set(final String attributeName, final Object value) {
    final String setterName = "set" + ucfirst(attributeName);

    /* Find setter */
    Method setter = getSetterMethod(setterName, clazz, value.getClass());

    if (setter == null) {
      throw RecipeException.noPublicSetter(setterName);
    }

    /* Invoke setter */
    try {
      setter.invoke(instance, value);
    } catch (IllegalAccessException exception) {
      throw RecipeException.noPublicSetter(setterName);
    } catch (InvocationTargetException exception) {
      throw RecipeException.exceptionInSetter(setterName, exception);
    }

    return this;
  }

  /**
   * Returns freshly cooked POJO. Call this method at the end of chain of
   * {@code set} methods.
   * <br>
   * Example :
   * <br>
   * <pre>
   * {@code
   *    MyClass myPojo = CakeMold.of(MyClass.class)
   *            .set("attribute 1", <value 1>)
   *            ...
   *            .set("attribute N", <value N>)
   *            .cook();
   * }
   * </pre>
   *
   * @return created instance
   */
  public T cook() {
    return this.instance;
  }

}
