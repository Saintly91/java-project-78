package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

  public NumberSchema required() {
    requiredFlag = true;
    return this;
  }

  public NumberSchema positive() {
    addRule("positive", number -> number > 0);
    return this;
  }

  public NumberSchema range(int minValue, int maxValue) {
    addRule("range", number -> (number >= minValue && number <= maxValue));
    return this;
  }
}
