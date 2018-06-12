package expression.operations;

public class LongOperation implements Operation<Long> {
    @Override
    public Long add(Long x, Long y) {
        return x + y;
    }

    @Override
    public Long subtract(Long x, Long y) {
        return x - y;
    }

    @Override
    public Long negate(Long x) {
        return -x;
    }

    @Override
    public Long multiply(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long divide(Long x, Long y) {
        return x / y;
    }

    @Override
    public Long parse(String number) {
        return Long.parseLong(number);
    }

    @Override
    public Long valueOf(int n) {
        return (long) n;
    }

    @Override
    public String toString(Long x) {
        return x.toString();
    }
}
