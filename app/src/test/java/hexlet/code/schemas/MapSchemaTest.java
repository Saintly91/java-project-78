package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    @Test
    void testDefaultMapValidation() {
        var v = new Validator();
        var schema = v.map();

        var data = new HashMap<String,String>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(data));
    }

    @Test
    void testRequiredMapValidation() {
        var v = new Validator();
        var schema = v.map();

        schema.required();

        var data = new HashMap<String,String>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeofMapValidation() {
        var v = new Validator();
        var schema = v.map();

        schema.sizeof(2);

        var data = new HashMap<String,String>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));

        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testAllRestrictionsWorkTogether() {
        var v = new Validator();
        var schema = v.map();

        schema.required().sizeof(2);
        var data = new HashMap<String, String>();

        data.put("key1", "value1");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    void testMapReplacePreviousValues() {
        var v = new Validator();
        var schema = v.map();

        schema.sizeof(2).sizeof(1);
        var data = new HashMap<String, String>();

        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        data.put("key2", "value2");
        assertFalse(schema.isValid(data));
    }
}
