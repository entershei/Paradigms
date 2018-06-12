package expression;

public class Const implements CommonExpression {
    protected Number valueDb;

    public Const(Number valueDb) {
        this.valueDb = valueDb;
    }

    Const(int value) {
        this.valueDb = value;
    }

    @Override
    public int evaluate(int x) {
        return valueDb.intValue();
    }

    @Override
    public double evaluate(double x) {
        return valueDb.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return valueDb.intValue();
    }
}
