package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

  @Test
  void testValidate() {
    var v = new Validator();
    var schema = v.string();

    assertInstanceOf(StringSchema.class, schema);
  }

  @Test
  void testValidateNumber() {
    var v = new Validator();
    var number = v.number();

    assertInstanceOf(NumberSchema.class, number);
  }
}
