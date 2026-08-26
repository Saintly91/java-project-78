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
}
