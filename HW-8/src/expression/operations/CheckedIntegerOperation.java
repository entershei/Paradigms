package expression.operations;

import expression.exceptions.OverflowException;

public class CheckedIntegerOperation implements Operation<Integer> {
    @Override
    public Integer add(Integer x, Integer y) {
        if ((x < 0 && y < 0 && Integer.MIN_VALUE - x > y)
                || (x > 0 && y > 0 && Integer.MAX_VALUE - x < y)) {
            return null;
        }

        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if ((x <= 0 && y >= 0 && Integer.MIN_VALUE + y > x) ||
                (x >= 0 && y <= 0 && Integer.MAX_VALUE + y < x)) {
            return null;
        }

        return x - y;
    }

    @Override
    public Integer negate(Integer x) {
        if (x == Integer.MIN_VALUE) {
            return null;
        }

        return -x;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        int mul = x * y;

        if ((y != 0 && x != mul / y) || (x == Integer.MIN_VALUE && y == -1)) {
            return null;
        }

        return mul;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            return null;
        }

        if (y == -1 && x == Integer.MIN_VALUE) {
            return null;
        }

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
