package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ValidatorTest {

    @Test
    void testValidate() {
        var v = new Validator();
        var schema = v.string();

        assertInstanceOf(StringSchema.class, schema);
    }

}
