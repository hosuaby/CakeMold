package io.hosuaby.cakemold;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Static helper functions.
 *
 * @author Alexei KLENIN
 */
class Utils {

  /**
   * Returns setter method for provided POJO class and class of attribute.
   * If class of attribute is a wrapper around primitive Java type method looks
   * for both setters accepting wrapper or primitive.
   * If setter not found returns null.
   *
   * @param methodName method name
   * @param pojoClass class of POJO
   * @param attributeClass class of attribute of setter
   *
   * @return setter method or null
   */
  static Method getSetterMethod(final String methodName,
                                final Class<?> pojoClass,
                                final Class<?> attributeClass) {
    try {
      return pojoClass.getDeclaredMethod(methodName, attributeClass);
    } catch (NoSuchMethodException setterNotFoundException) {

      /* Try to find with primitive type in signature */
      if (hasPrimitiveType(attributeClass)) {
        try {
          return pojoClass.getDeclaredMethod(
              methodName, getPrimitiveType(attributeClass));
        } catch (NoSuchMethodException setterNotFoundAgainException) {
          /* Ignore this exception because null will be returned */
        }
      }
    }

    return null;
  }

  /**
   * Checks if provided class has equivalent primitive Java type.
   *
   * @param clazz class
   * @return true - class has equivalent Java type, false - doesn't have
   */
  static boolean hasPrimitiveType(final Class<?> clazz) {
    for (Field field : clazz.getFields()) {
      if ("TYPE".equals(field.getName())) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns class that represents a primitive Java type of wrapper class. If
   * provided class doesn't have primitive type returns null.
   *
   * @param clazz wrapper class
   * @return class of primitive Java type or null
   */
  static Class<?> getPrimitiveType(final Class<?> clazz) {
    try {
      return (Class<?>) clazz.getField("TYPE").get(null);
    } catch (IllegalAccessException
        | NoSuchFieldException unexpectedException) {
      return null;
    }
  }

  /**
   * Returns string with capitalized first letter.
   *
   * @param str string
   * @return string with capitalized first letter
   */
  static String ucfirst(final String str) {
    return str.isEmpty()
        ? ""
        : str.substring(0, 1).toUpperCase() + str.substring(1);
  }

}
