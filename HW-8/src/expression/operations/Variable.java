package expression.operations;

import expression.TripleExpression;
import expression.exceptions.ParseException;

import java.util.NoSuchElementException;

public class Variable<T> implements TripleExpression<T> {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public T evaluate(T x, T y, T z) {
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
