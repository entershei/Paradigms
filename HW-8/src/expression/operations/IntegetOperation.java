package expression.operations;

public class IntegetOperation implements Operation<Integer> {
    @Override
    public Integer add(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Integer negate(Integer x) {
        return -x;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        return x / y;
    }

    @Override
    public Integer parse(String number) {
        return Integer.parseInt(number);
    }

    @Override
    public Integer valueOf(int n) {
        return n;
    }

    @Override
    public String toString(Integer x) {
        return x.toString();
    }
}
