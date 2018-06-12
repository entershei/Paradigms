package expression.operations;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {
    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        return x.divide(y);
    }

    @Override
    public BigInteger parse(String number) {
        return new BigInteger(number);
    }

    @Override
    public BigInteger valueOf(int n) {
        return BigInteger.valueOf(n);
    }

    @Override
    public String toString(BigInteger x) {
        return x.toString();
    }
}
