package expression;

import expression.exceptions.ArgumentException;
import expression.exceptions.CalculationException;
import expression.exceptions.OverflowException;
import expression.exceptions.ParseException;

import java.util.Objects;

public abstract class AbstractUnaryExpression implements TripleExpression {
    protected final TripleExpression operand;

    public AbstractUnaryExpression(TripleExpression operand) {
        this.operand = operand;
    }

    @Override
    public int evaluate(int x, int y, int z) throws CalculationException, ParseException {
        return operation(operand.evaluate(x, y, z));
    }


    protected abstract int operation(int x) throws OverflowException, ArgumentException;
}
