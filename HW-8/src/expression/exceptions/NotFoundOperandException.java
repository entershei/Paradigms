package expression.exceptions;

public class NotFoundOperandException extends ParseException {
    public NotFoundOperandException(int pos, String str) {
        super("Not found operand before binary operation " + str.substring(0, pos) + "  --> " + str.substring(pos));
    }
}
