package expression;

public class Divide extends AbstractBinaryExpression {
    public Divide(CommonExpression operand1, CommonExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected double operation(double operand1, double operand2) {
        return operand1 / operand2;
    }

    @Override
    protected int operation(int operand1, int operand2) {
        return operand1 / operand2;
    }
}