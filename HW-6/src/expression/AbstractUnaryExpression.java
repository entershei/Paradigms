package expression;

import java.util.Objects;

public abstract class AbstractUnaryExpression implements CommonExpression {
    protected final CommonExpression operand;

    public AbstractUnaryExpression(CommonExpression operand) {
        Objects.requireNonNull(operand, "operand1 must not be null");

        this.operand = operand;
    }

    @Override
    public int evaluate(int x) {
        return operation(operand.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return operation(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operation(operand.evaluate(x, y, z));
    }


    protected abstract int operation(int x);

    protected abstract double operation(double x);
}
