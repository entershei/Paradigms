package expression;

public class Negate extends AbstractUnaryExpression{
    public Negate(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected int operation(int x) {
        return -x;
    }

    @Override
    protected double operation(double x) {
        return -x;
    }
}
