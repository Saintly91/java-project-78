package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema <Map<?, ?>> {
    public MapSchema required() {
        addRule("required",
                map -> map != null
        );
        return this;
    }

    public MapSchema sizeof(int value) {
        addRule("sizeof",
                map -> map == null
                || map.size() == value
        );
        return this;
    }

    public  MapSchema shape(Map <String, BaseSchema<String>> schemas) {
        addRule(
                "shape",
                map -> map == null
                        || schemas.entrySet()
                        .stream()
                        .allMatch(entry -> {
                            String value = (String) map.get(entry.getKey());
                            BaseSchema<String> schema = entry.getValue();

                            return schema.isValid(value);
                        })
        );
        return this;
    }
}
