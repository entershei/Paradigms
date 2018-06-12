package expression;

public class Xor extends AbstractBinaryExpression {
    public Xor(CommonExpression operand1, CommonExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected double operation(double operand1, double operand2) {
        throw new ExceptionInInitializerError("can't double XOR double");
    }

    @Override
    protected int operation(int operand1, int operand2) {
        return operand1 ^ operand2;
    }
}
