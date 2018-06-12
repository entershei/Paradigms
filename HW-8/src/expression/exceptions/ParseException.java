package expression.exceptions;

public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, int pos, String expression) {
        super(message + "    " + expression.substring(0, pos) + " --> " + (expression + "   ").substring(pos));
    }
}
