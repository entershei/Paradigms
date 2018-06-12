package expression;

import expression.parser.ExpressionParser;

//x * x -2x+1
public class Main {
    public static void main(final String[] args) {
        ExpressionParser x = new ExpressionParser();

        CommonExpression ans = x.parse("x-x+y-y+z-(z)");
        System.err.println(ans.evaluate(0, 0, 1));
    }
}
