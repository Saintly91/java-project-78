package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private int minLength;
    private String substring;

    @Override
    public boolean isValid(String value) {
        if (!super.isValid(value)) {
            return false;
        }

        if (value == null) {
            return true;
        }

        if (value.isEmpty() && required) {
            return false;
        }

        if (value.isEmpty()) {
            return true;
        }

        if (minLength > 0 && value.length() < minLength) {
            return false;
        }

        if (substring != null && !value.contains(substring)) {
            return false;
        }

        return true;
    }

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }
    public StringSchema minLength(int value) {
        minLength = value;
        return this;
    }

    public StringSchema contains(String text) {
        substring = text;
        return this;
    }
}