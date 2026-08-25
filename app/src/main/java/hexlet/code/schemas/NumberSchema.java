package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive;
    private boolean range = false;
    private int minRange;
    private int maxRange;

    @Override
    public boolean isValid(Integer value) {

        if (!super.isValid(value)) {
            return false;
        }

        if (value == null) {
            return true;
        }

        if (value < 1 && positive) {
            return false;
        }

        if ((value < minRange || value > maxRange) && range) {
            return false;
        }

        return true;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.range = true;
        return this;
    }
}
