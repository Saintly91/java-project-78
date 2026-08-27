package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    void testShapeValidation() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> data = new HashMap<>();
        data.put("firstName", "John");
        data.put("lastName", "Smith");

        assertTrue(schema.isValid(data));
    }

    @Test
    void testShapeValidationWithoutRestriction() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schema.shape(schemas);

        assertTrue(schema.isValid(null));
    }

    @Test
    void testShapeWithInvalidNullValue() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> data = new HashMap<>();
        data.put("firstName", "John");
        data.put("lastName", null);

        assertFalse(schema.isValid(data));
    }

    @Test
    void testShapeWithInvalidNestedRestriction() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> data = new HashMap<>();
        data.put("firstName", "John");
        data.put("lastName", "B");

        assertFalse(schema.isValid(data));
    }

    @Test
    void testShapeWithMissingRequiredKey() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required());

        schema.shape(schemas);

        Map<String, String> data = new HashMap<>();
        data.put("firstName", "John");

        assertFalse(schema.isValid(data));
    }

    @Test
    void testShapeWithAdditionalRestrictions() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("email", v.string().required().contains("@"));

        schema.shape(schemas);

        Map<String, String> data = new HashMap<>();

        data.put("email", "example@example.com");
        assertTrue(schema.isValid(data));

        data.put("email", "exampleexample.com");
        assertFalse(schema.isValid(data));
    }
}
