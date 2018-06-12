package expression;

import expression.exceptions.CalculationException;
import expression.exceptions.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws CalculationException, ParseException;
}
