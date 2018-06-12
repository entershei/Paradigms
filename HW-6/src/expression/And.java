package expression;

public class And extends AbstractBinaryExpression {
    public And(CommonExpression operand1, CommonExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected double operation(double operand1, double operand2) {
        throw new ExceptionInInitializerError("can't double AND double");
    }

    @Override
    protected int operation(int operand1, int operand2) {
        return operand1 & operand2;
    }
}
