package expression.operations;

import expression.TripleExpression;
import expression.exceptions.ArgumentException;
import expression.exceptions.CalculationException;
import expression.exceptions.OverflowException;
import expression.exceptions.ParseException;
import expression.operations.Operation;

public abstract class AbstractUnaryExpression<T> implements TripleExpression<T> {
    protected final TripleExpression<T> operand;
    protected final Operation<T> op;

    public AbstractUnaryExpression(Operation<T> op, TripleExpression<T> operand) {
        this.operand = operand;
        this.op = op;
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalculationException, ParseException {
        return operation(operand.evaluate(x, y, z));
    }

    protected abstract T operation(T x) throws OverflowException, ArgumentException;
}
