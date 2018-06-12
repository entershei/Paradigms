package expression.exceptions;

public class DivisionByZeroException extends CalculationException {
    DivisionByZeroException(String message) {
        super(message);
    }
}
