package expression.exceptions;

import expression.AbstractUnaryExpression;
import expression.TripleExpression;

public class CheckedPow10 extends AbstractUnaryExpression {
    public CheckedPow10(TripleExpression operand) {
        super(operand);
    }

    @Override
    protected int operation(int x) throws OverflowException, ArgumentException {
        if (x > 9) {
            throw new OverflowException("Overflow in pow10");
        }

        if (x < 0) {
            throw new ArgumentException("In pow10 argument is " + x);
        }

        return pow10(x);
    }

    private int pow10(int x) {
        int ans = 1;
        for (int i = 0; i < x; ++i) {
            ans *= 10;
        }

        return ans;
    }
}
