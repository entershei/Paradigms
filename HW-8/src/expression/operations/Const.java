package expression.operations;

import expression.TripleExpression;
import expression.exceptions.CalculationException;
import expression.exceptions.ParseException;

public class Const<T> implements TripleExpression<T> {
    protected T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalculationException, ParseException {
        return value;
    }
}
