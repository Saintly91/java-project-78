package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        requiredFlag = true;

        addRule("required",
                string -> !string.isEmpty()
        );
        return this;
    }

    public StringSchema minLength(int value) {
        addRule("minLength",
                string -> string.isEmpty()
                || string.length() >= value
        );
        return this;
    }

    public StringSchema contains(String text) {
        addRule("contains",
                string -> string.isEmpty()
                || string.contains(text)
        );
        return this;
    }
}
