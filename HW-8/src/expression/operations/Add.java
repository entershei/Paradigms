package expression.operations;

import expression.exceptions.CalculationException;
import expression.operations.AbstractBinaryExpression;
import expression.TripleExpression;
import expression.operations.Operation;

public class Add<T> extends AbstractBinaryExpression<T> {
    public Add(Operation<T> op, TripleExpression<T> operand1, TripleExpression<T> operand2) {
        super(op, operand1, operand2);
    }


    @Override
    protected T operation(T operand1, T operand2) throws CalculationException {
        return op.add(operand1, operand2);
    }
}
