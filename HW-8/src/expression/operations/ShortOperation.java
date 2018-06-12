package expression.operations;

public class ShortOperation implements Operation<Short> {
    @Override
    public Short add(Short x, Short y) {
        return (short) (x + y);
    }

    @Override
    public Short subtract(Short x, Short y) {
        return (short) (x - y);
    }

    @Override
    public Short negate(Short x) {
        return (short) -x;
    }

    @Override
    public Short multiply(Short x, Short y) {
        return (short) (x * y);
    }

    @Override
    public Short divide(Short x, Short y) {
        return (short) (x / y);
    }

    @Override
    public Short parse(String number) {
        return Short.parseShort(number);
    }

    @Override
    public Short valueOf(int n) {
        return (short) n;
    }

    @Override
    public String toString(Short x) {
        return x.toString();
    }
}
