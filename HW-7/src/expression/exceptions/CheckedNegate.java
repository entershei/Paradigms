package expression.exceptions;

import expression.AbstractUnaryExpression;
import expression.TripleExpression;

public class CheckedNegate extends AbstractUnaryExpression {
    public CheckedNegate(TripleExpression operand) {
        super(operand);
    }

    @Override
    protected int operation(int x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in Negate");
        }

        return -x;
    }
}
