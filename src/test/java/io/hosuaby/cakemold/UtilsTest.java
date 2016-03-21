package io.hosuaby.cakemold;

import static org.assertj.core.api.Assertions.assertThat;

import io.hosuaby.cakemold.pojos.Person;
import org.junit.Test;

import java.util.Date;

public class UtilsTest {

  @Test
  public void testGetSetterMethod_setFirstName() {
    assertThat(Utils
        .getSetterMethod("setFirstName", Person.class, String.class))
        .isNotNull();
  }

  @Test
  public void testGetSetterMethod_setFirstName_wrongType() {
    assertThat(Utils
        .getSetterMethod("setFirstName", Person.class, Date.class))
        .isNull();
  }

  @Test
  public void testGetSetterMethod_setAge_int() {
    assertThat(Utils
        .getSetterMethod("setAge", Person.class, int.class))
        .isNotNull();
  }

  @Test
  public void testGetSetterMethod_setAge_Integer() {
    assertThat(Utils
        .getSetterMethod("setAge", Person.class, Integer.class))
        .isNotNull();
  }

  @Test
  public void testGetSetterMethod_unknownMethod() {
    assertThat(Utils
        .getSetterMethod("setBirthPlace", Person.class, String.class))
        .isNull();
  }

  @Test
  public void testGetSetterMethod_unknownMethodAcceptingPrimitiveType() {
    assertThat(Utils
        .getSetterMethod("setLikeApples", Person.class, Boolean.class))
        .isNull();
  }

  @Test
  public void testHasPrimitiveType_Integer() {
    assertThat(Utils.hasPrimitiveType(Integer.class)).isTrue();
  }

  @Test
  public void testHasPrimitiveType_String() {
    assertThat(Utils.hasPrimitiveType(String.class)).isFalse();
  }

  @Test
  public void testGetPrimitiveType_Integer() {
    assertThat(Utils.getPrimitiveType(Integer.class)).isEqualTo(int.class);
  }

  @Test
  public void testGetPrimitiveType_String() {
    assertThat(Utils.getPrimitiveType(String.class)).isNull();
  }

  @Test
  public void testUcfirst() {
    assertThat(Utils.ucfirst("toto")).isEqualTo("Toto");
  }

  @Test
  public void testUcfirst_emptyString() {
    assertThat(Utils.ucfirst("")).isEqualTo("");
  }

}
