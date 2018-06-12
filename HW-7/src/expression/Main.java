package expression;

import expression.exceptions.CalculationException;
import expression.exceptions.ExpressionParser;
import expression.exceptions.ParseException;

public class Main {
    public static void main(final String[] args) throws ParseException, CalculationException {
        ExpressionParser x = new ExpressionParser();

        TripleExpression ans = x.parse("(x * (y)");
        //TripleExpression ans = x.parse("1 + (* y * z) + 2");
        System.err.println(ans.evaluate(1364693298, 867966562, 1175344875));
    }
}

/*
log10
log10()

* 5
7 /
)
(
 */