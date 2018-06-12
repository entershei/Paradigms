package expression.operations;

import expression.exceptions.CalculationException;
import expression.operations.AbstractBinaryExpression;
import expression.TripleExpression;
import expression.operations.Operation;

public class Multiply<T> extends AbstractBinaryExpression<T> {
    public Multiply(Operation<T> op, TripleExpression operand1, TripleExpression operand2) {
        super(op, operand1, operand2);
    }

    @Override
    protected T operation(T operand1, T operand2) throws CalculationException {
        return op.multiply(operand1, operand2);
    }
}
