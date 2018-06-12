package expression.exceptions;

import expression.AbstractBinaryExpression;
import expression.TripleExpression;

public class CheckedMultiply extends AbstractBinaryExpression {
    public CheckedMultiply(TripleExpression operand1, TripleExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int operation(int operand1, int operand2) throws OverflowException {
        int mul = operand1 * operand2;

        if ((operand2 != 0 && operand1 != mul / operand2) || (operand1 == Integer.MIN_VALUE && operand2 == -1)) {
            throw new OverflowException("Overflow in Multiply");
        }

        return mul;
    }
}
