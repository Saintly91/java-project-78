package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

  public StringSchema required() {
    requiredFlag = true;
    return this;
  }

  public StringSchema minLength(int value) {
    addRule("minLength", string -> string.length() >= value);
    return this;
  }

  public StringSchema contains(String text) {
    addRule("contains", string -> string.contains(text));
    return this;
  }
}
