package expression;

import expression.exceptions.CalculationException;
import expression.exceptions.ParseException;

import java.util.Objects;

public abstract class AbstractBinaryExpression implements TripleExpression {
    protected final TripleExpression operand1, operand2;

    //Pre: operand1 != null && operand2 != null
    protected AbstractBinaryExpression(TripleExpression operand1, TripleExpression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    //Post R = Expression(x)
    @Override
    public int evaluate(int x, int y, int z) throws CalculationException, ParseException {
        int operand1_int = operand1.evaluate(x, y, z);
        int operand2_int = operand2.evaluate(x, y, z);

        return operation(operand1_int, operand2_int);
    }


    protected abstract int operation(int operand1, int operand2) throws CalculationException;
}
