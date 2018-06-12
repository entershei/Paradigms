package expression.exceptions;

import expression.AbstractUnaryExpression;
import expression.TripleExpression;

public class CheckedLog10 extends AbstractUnaryExpression {
    public CheckedLog10(TripleExpression operand) {
        super(operand);
    }

    @Override
    protected int operation(int x) throws OverflowException, ArgumentException {
        if (x <= 0) {
            throw new ArgumentException("In log10 argument is " + x);
        }

        return log10(x);
    }

    private int log10(int x) {
        int log = 0;

        //System.err.println("? " + x);

        while (x != 0) {
            ++log;
            x /= 10;
        }

        //System.err.println("?? " + (log - 1));


        return log - 1;
    }
}
