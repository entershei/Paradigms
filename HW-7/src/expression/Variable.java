package expression;

import expression.exceptions.ParseException;

import java.util.NoSuchElementException;

public class Variable implements TripleExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new AssertionError("Variable should be x, y or z");
        }
    }
}
