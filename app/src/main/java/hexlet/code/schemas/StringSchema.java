package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addRule("required",
                string -> string != null
                && !string.isEmpty()
        );
        return this;
    }

    public StringSchema minLength(int value) {
        addRule("minLength",
                string -> string == null
                || string.isEmpty()
                || string.length() >= value
        );
        return this;
    }

    public StringSchema contains(String text) {
        addRule("contains",
                string -> string == null
                || string.isEmpty()
                || string.contains(text)
        );
        return this;
    }
}
