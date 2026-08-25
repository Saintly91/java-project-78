package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    @Test
    void testDefaultNumberValidation() {
        var v = new Validator();
        var number = v.number();

        assertTrue(number.isValid(null));
        assertTrue(number.isValid(5));
    }

    @Test
    void testRequiredMakesNullNumberInvalid() {
        var v = new Validator();
        var number = v.number();

        number.required();

        assertFalse(number.isValid(null));
        assertTrue(number.isValid(5));
    }

    @Test
    void testPositiveNumberValidation() {
        var v = new Validator();
        var number = v.number();

        number.positive();

        assertTrue(number.isValid(null));
        assertTrue(number.isValid(5));
        assertFalse(number.isValid(-1));
        assertFalse(number.isValid(0));
    }

    @Test
    void testRangeNumberValidation() {
        var v = new Validator();
        var number = v.number();

        number.range(5, 10);

        assertTrue(number.isValid(5));
        assertTrue(number.isValid(10));
        assertFalse(number.isValid(4));
        assertFalse(number.isValid(11));
    }
    @Test
    void testAllRestrictionsWorkTogether() {
        var v = new Validator();
        var number = v.number();

        number.positive().range(-2, 5);

        assertFalse(number.isValid(-2));
        assertTrue(number.isValid(5));
    }

    @Test
    void testRangesReplacesPreviousValue () {
        var v = new Validator();
        var number = v.number();

        number.range(5, 10).range(6, 9);

        assertFalse(number.isValid(5));
        assertFalse(number.isValid(10));
        assertTrue(number.isValid(6));
        assertTrue(number.isValid(9));
    }
}
