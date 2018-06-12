package expression.operations;


import expression.exceptions.CalculationException;
import expression.operations.AbstractBinaryExpression;
import expression.TripleExpression;
import expression.operations.Operation;

public class Subtract<T> extends AbstractBinaryExpression<T> {
    public Subtract(Operation<T> op, TripleExpression operand1, TripleExpression operand2) {
        super(op, operand1, operand2);
    }

    /*protected int operation(int operand1, int operand2) throws OverflowException {
        if ((operand1 <= 0 && operand2 >= 0 && Integer.MIN_VALUE + operand2 > operand1) ||
                (operand1 >= 0 && operand2 <= 0 && Integer.MAX_VALUE + operand2 < operand1)) {
            throw new OverflowException("Overflow in Subtract");
        }

        return operand1 - operand2;
    }*/

    @Override
    protected T operation(T operand1, T operand2) throws CalculationException {
        return op.subtract(operand1, operand2);
    }
}
