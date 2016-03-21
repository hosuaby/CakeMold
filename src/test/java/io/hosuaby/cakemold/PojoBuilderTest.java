package io.hosuaby.cakemold;

import static org.assertj.core.api.Assertions.assertThat;

import io.hosuaby.cakemold.pojos.Being;
import io.hosuaby.cakemold.pojos.Fool;
import io.hosuaby.cakemold.pojos.Hermit;
import io.hosuaby.cakemold.pojos.Person;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PojoBuilderTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testNominalScenario() {
    Person person = CakeMold.of(Person.class)
        .set("firstName", "Bob")
        .set("lastName", "SquarePants")
        .set("email", "sponge.bob@bikinibottom.io")
        .set("age", 22)
        .cook();

    assertThat(person)
        .hasFieldOrPropertyWithValue("firstName", "Bob")
        .hasFieldOrPropertyWithValue("lastName", "SquarePants")
        .hasFieldOrPropertyWithValue("email", "sponge.bob@bikinibottom.io")
        .hasFieldOrPropertyWithValue("age", 22);
  }

  @Test
  public void testBuildClassWithNoPublicConstructor() {
    expectedException.expect(RecipeException.class);
    expectedException.expectMessage(String.format(
        RecipeException.NO_PUBLIC_NULLARY_CONSTRUCTOR,
        Hermit.class.getName()));

    CakeMold.of(Hermit.class)
        .set("nickname", "Tudor")
        .cook();
  }

  @Test
  public void testBuildAbstractClass() {
    expectedException.expect(RecipeException.class);
    expectedException.expectMessage(String.format(
        RecipeException.ABSTRACT_CLASS_INSTANTIATION,
        Being.class.getName()));

    CakeMold.of(Being.class)
        .set("members", 4)
        .cook();
  }

  @Test
  public void testBuildClassWithExceptionInConstructor() {
    expectedException.expect(RecipeException.class);
    expectedException.expectMessage(String.format(
        RecipeException.EXCEPTION_IN_CONSTRUCTOR,
        Fool.class.getName()));

    CakeMold.of(Fool.class)
        .set("favoriteToy", "ball")
        .cook();
  }

  @Test
  public void testBuildClass_noSetterFound() {
    expectedException.expect(RecipeException.class);
    expectedException.expectMessage(String.format(
        RecipeException.NO_PUBLIC_SETTER, "setNickname"));

    Person person = CakeMold.of(Person.class)
        .set("firstName", "Bob")
        .set("lastName", "SquarePants")
        .set("email", "sponge.bob@bikinibottom.io")
        .set("age", 22)
        .set("nickname", "Crazy sponge")
        .cook();
  }

  @Test
  public void testBuildClass_setterNotPublic() {
    expectedException.expect(RecipeException.class);
    expectedException.expectMessage(String.format(
        RecipeException.NO_PUBLIC_SETTER, "setHappiness"));

    Person person = CakeMold.of(Person.class)
        .set("firstName", "Bob")
        .set("lastName", "SquarePants")
        .set("email", "sponge.bob@bikinibottom.io")
        .set("age", 22)
        .set("happiness", true)
        .cook();
  }

  @Test
  public void testBuildClass_exceptionInSetter() {
    expectedException.expect(RecipeException.class);
    expectedException.expectMessage(String.format(
        RecipeException.EXCEPTION_IN_SETTER, "setFavoriteToy"));

    Person person = CakeMold.of(Person.class)
        .set("firstName", "Bob")
        .set("lastName", "SquarePants")
        .set("email", "sponge.bob@bikinibottom.io")
        .set("age", 22)
        .set("favoriteToy", "Soap bubbles")
        .cook();
  }

}
