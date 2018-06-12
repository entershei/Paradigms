package expression;

import expression.exceptions.CalculationException;
import expression.exceptions.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression {
    int evaluate(int x, int y, int z) throws CalculationException, ParseException;
}
