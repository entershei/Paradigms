package expression.exceptions;

import expression.AbstractBinaryExpression;
import expression.TripleExpression;

public class CheckedDivide extends AbstractBinaryExpression {
    public CheckedDivide(TripleExpression operand1, TripleExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int operation(int operand1, int operand2) throws DivisionByZeroException, OverflowException {
        if (operand2 == 0) {
            throw new DivisionByZeroException("Division by zero");
        }

        if (operand2 == -1 && operand1 == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in divide");
        }

        return operand1 / operand2;
    }
}
