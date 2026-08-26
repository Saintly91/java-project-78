package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addRule("required",
                number -> number != null
        );
        return this;
    }

    public NumberSchema positive() {
        addRule("positive",
                number -> number == null
                || number > 0
        );
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        addRule("range",
                number -> number == null
                || (number >= minValue && number <= maxValue)
        );
        return this;
    }
}
