package expression.exceptions;

import expression.AbstractBinaryExpression;
import expression.TripleExpression;

public class CheckedAdd extends AbstractBinaryExpression {
    public CheckedAdd(TripleExpression operand1, TripleExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int operation(int operand1, int operand2) throws OverflowException {
        if ((operand1 < 0 && operand2 < 0 && Integer.MIN_VALUE - operand1 > operand2)
                || (operand1 > 0 && operand2 > 0 && Integer.MAX_VALUE - operand1 < operand2)) {
            throw new OverflowException("Overflow in add");
        }

        return operand1 + operand2;
    }
}
