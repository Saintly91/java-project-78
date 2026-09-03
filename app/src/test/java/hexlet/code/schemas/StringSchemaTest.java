package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

public class StringSchemaTest {

  @Test
  void testDefaultStringValidation() {
    var v = new Validator();
    var schema = v.string();

    assertTrue(schema.isValid(null));
    assertTrue(schema.isValid(""));
    assertTrue(schema.isValid("some text"));
  }

  @Test
  void testRequiredMakesNullAndEmptyStringInvalid() {
    var v = new Validator();
    var schema = v.string();

    schema.required();

    assertFalse(schema.isValid(null));
    assertFalse(schema.isValid(""));
    assertTrue(schema.isValid("some text"));
  }

  @Test
  void testMinLength() {
    var v = new Validator();
    var schema = v.string();

    schema.minLength(5);

    assertTrue(schema.isValid(""));
    assertTrue(schema.isValid(null));
    assertFalse(schema.isValid("four"));
    assertTrue(schema.isValid("hello"));
    assertTrue(schema.isValid("hexlet"));
  }

  @Test
  void testMinLengthReplacePreviousValue() {
    var v = new Validator();
    var schema = v.string();

    schema.minLength(10).minLength(4);

    assertTrue(schema.isValid("hexlet"));
  }

  @Test
  void testContains() {
    var v = new Validator();
    var schema = v.string();

    schema.contains("wh");
    assertTrue(schema.isValid(""));
    assertTrue(schema.isValid(null));
    assertTrue(schema.isValid("what does the fox say"));

    schema.contains("what");
    assertTrue(schema.isValid("what does the fox say"));

    schema.contains("whatthe");
    assertFalse(schema.isValid("what does the fox say"));
    assertFalse(schema.isValid("what does the fox say"));
  }

  @Test
  void testContainsReplacesPreviousValue() {
    var v = new Validator();
    var schema = v.string();

    schema.contains("whatthe").contains("what");

    assertTrue(schema.isValid("what does the fox say"));
  }

  @Test
  void testAllRestrictionsWorkTogether() {
    var v = new Validator();
    var schema = v.string();

    schema.required().minLength(10).contains("hex");

    assertFalse(schema.isValid("hex"));
    assertFalse(schema.isValid(null));
    assertFalse(schema.isValid(""));
    assertFalse(schema.isValid("abcdefghij"));
    assertTrue(schema.isValid("hexabcdefg"));
  }
}
