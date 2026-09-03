package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

  protected boolean requiredFlag = false;

  private final Map<String, Predicate<T>> rules = new LinkedHashMap<>();

  protected void addRule(String name, Predicate<T> rule) {
    rules.put(name, rule);
  }

  public boolean isValid(T value) {

    if (value == null || isValueEmpty(value)) {
      return !requiredFlag;
    }
    return rules.values().stream().allMatch(rule -> rule.test(value));
  }

  protected boolean isValueEmpty(T value) {
    if (value instanceof String) {
      return value.toString().isEmpty();
    }
    return false;
  }
}
