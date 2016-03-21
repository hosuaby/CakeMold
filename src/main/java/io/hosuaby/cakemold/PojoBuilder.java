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
   * Sets attribute using setter found via reflexion. Returns current builder.
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
   * Returns freshly cooked POJO.
   *
   * @return created instance
   */
  public T cook() {
    return this.instance;
  }

}
