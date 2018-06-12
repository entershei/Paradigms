package expression.operations;

import expression.exceptions.ArgumentException;
import expression.exceptions.OverflowException;
import expression.operations.AbstractUnaryExpression;
import expression.TripleExpression;
import expression.operations.Operation;

public class Negate<T> extends AbstractUnaryExpression<T> {
    public Negate(Operation<T> op, TripleExpression<T> operand) {
        super(op, operand);
    }

    @Override
    protected T operation(T x) throws OverflowException, ArgumentException {
        /*if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in Negate");
        }

        return -x;
        */

        return op.negate(x);
    }
}
