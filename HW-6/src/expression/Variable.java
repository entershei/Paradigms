package expression;

import java.util.NoSuchElementException;

public class Variable implements CommonExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
            default: throw new NoSuchElementException("Name is not x or y or z, found " + name);
        }
    }
}
