package expression;

import java.util.Objects;

public abstract class AbstractExpression implements AnyExpression {
    protected final AnyExpression operand1, operand2;

    //Pre: operand1 != null && operand2 != null
    AbstractExpression(AnyExpression operand1, AnyExpression operand2) {
        Objects.requireNonNull(operand1, "operand1 must not be null");
        Objects.requireNonNull(operand2, "operand2 must not be null");

        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    //Post R = Expression(x)
    @Override
    public int evaluate(int x) {
        int operand1_int = operand1.evaluate(x);
        int operand2_int = operand2.evaluate(x);

        return operation(operand1_int, operand2_int);
    }

    //Post R = Expression(x)
    @Override
    public double evaluate(double x) {
        double operand1Double = operand1.evaluate(x);
        double operand2Double = operand2.evaluate(x);

        return operation(operand1Double, operand2Double);
    }


    protected abstract double operation(double operand1_db, double operand2_db);

    protected abstract int operation(int operand1, int operand2);
}
