package expression;

public class Const implements AnyExpression {
    protected Number valueDb;

    Const(double valueDb) {
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
}
