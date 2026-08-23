package hexlet.code.schemas;

public class StringSchema {
    private boolean required;
    private int minLength;
    private String substring;

    public boolean isValid(String value) {
        if ((value == null || value.isEmpty()) && required) {
            return false;
        }

        if ((value == null || value.isEmpty()) && !required) {
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

    public StringSchema required() {
        this.required = true;
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