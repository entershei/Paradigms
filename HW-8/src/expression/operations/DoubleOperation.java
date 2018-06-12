package expression.operations;

public class DoubleOperation implements Operation<Double> {
    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double parse(String number) {
        return Double.parseDouble(number);
    }

    @Override
    public Double valueOf(int n) {
        return (double) n;
    }

    @Override
    public String toString(Double x) {
        return x.toString();
    }
}
