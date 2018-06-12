package expression.operations;

import expression.TripleExpression;
import expression.exceptions.CalculationException;
import expression.exceptions.ParseException;
import expression.operations.Operation;

public abstract class AbstractBinaryExpression<T> implements TripleExpression<T> {
    protected final TripleExpression<T> operand1, operand2;
    protected Operation<T> op;

    protected AbstractBinaryExpression(Operation<T> op, TripleExpression operand1, TripleExpression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.op = op;
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalculationException, ParseException {
        T operand1_T = operand1.evaluate(x, y, z);
        T operand2_T = operand2.evaluate(x, y, z);

        return operation(operand1_T, operand2_T);
    }


    protected abstract T operation(T operand1, T operand2) throws CalculationException;
}
