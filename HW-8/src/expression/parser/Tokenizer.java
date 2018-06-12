package expression.parser;

import expression.exceptions.NotFoundOperandException;
import expression.exceptions.ParseException;
import expression.operations.Operation;

public class Tokenizer<T> {
    final private String str;
    private int ind;
    private String name;
    private T value;
    private boolean canUnary = true;
    private boolean CanNotBinaryOperation = true;
    private Operation<T> op;

    private char symbol() {
        return str.charAt(ind);
    }

    private char nextSymbol() {
        skipSpaces();
        return str.charAt(ind + 1);
    }

    private boolean hasNextSymbol() {
        return ind + 1 < str.length();
    }

    public Tokenizer(Operation<T> opNew, String str) {
        this.str = str;
        op = opNew;
    }

    public boolean hasNextToken() {
        skipSpaces();

        return ind < str.length();
    }

    public Token nextToken() throws ParseException {
        if (!hasNextToken()) {
            throw new ParseException("Hasn't token, but you want next token", ind, str);
        }

        skipSpaces();

        char nextSymbol = ' ';
        if (hasNextSymbol()) {
            nextSymbol = nextSymbol();
        }

        skipSpaces();

        String curName;

        switch (symbol()) {
            case '+':
                if (CanNotBinaryOperation) {
                    throw new NotFoundOperandException(ind, str);
                }

                CanNotBinaryOperation = true;
                ++ind;
                canUnary = true;
                return Token.ADD;
            case '-':
                if (canUnary) {
                    CanNotBinaryOperation = false;

                    if (Character.isDigit(nextSymbol)) {
                        number();

                        canUnary = false;

                        return Token.CONST;
                    }

                    ++ind;
                    return Token.NOT;
                } else {
                    if (CanNotBinaryOperation) {
                        throw new NotFoundOperandException(ind, str);
                    }

                    CanNotBinaryOperation = true;

                    ++ind;
                    canUnary = true;

                    skipSpaces();

                    if (!Character.isDigit(symbol()) && !Character.isAlphabetic(symbol()) && symbol() != '('
                            && symbol() != '-') {
                        throw new ParseException("Not found second operand after - ", ind, str);
                    }

                    return Token.SUB;
                }
            case 'p':
                CanNotBinaryOperation = false;
                Name();
                number();

                curName = name + op.toString(value);

                if (!curName.equals("pow10")) {
                    throw new ParseException("Expected pow10; Found " + name, ind, str);
                }

                if (!canUnary) {
                    throw new ParseException("Found pow10, but can't unary operation", ind, str);
                }

                return Token.POW10;
            case 'l':
                CanNotBinaryOperation = false;
                Name();
                number();

                curName = name + op.toString(value);

                if (!curName.equals("log10")) {
                    throw new ParseException("Expected log10; Found " + name, ind, str);
                }

                if (!canUnary) {
                    throw new ParseException("Found log10, but can't unary operation", ind, str);
                }

                return Token.LOG10;
            case '*':
                if (CanNotBinaryOperation) {
                    throw new NotFoundOperandException(ind, str);
                }

                CanNotBinaryOperation = true;

                ++ind;
                canUnary = true;
                return Token.MUL;
            case '/':
                if (CanNotBinaryOperation) {
                    throw new NotFoundOperandException(ind, str);
                }

                CanNotBinaryOperation = true;

                ++ind;
                canUnary = true;
                return Token.DIV;
            case '(':
                CanNotBinaryOperation = true;
                ++ind;
                canUnary = true;
                return Token.LEFT_PARENTHESIS;
            case ')':
                CanNotBinaryOperation = false;
                ++ind;
                canUnary = false;
                return Token.RIGHT_PARENTHESIS;
            default:
                CanNotBinaryOperation = false;
                canUnary = false;

                if (Character.isAlphabetic(symbol())) {
                    int startName = ind;

                    Name();

                    if (!name.equals("x") && !name.equals("y") && !name.equals("z")) {
                        throw new ParseException("Variable should be x, y or z; Found " + name, startName, str);
                    }
                    return Token.VARIABLE;
                } else if (Character.isDigit(symbol())) {
                    number();

                    return Token.CONST;
                } else {
                    throw new ParseException("Can't parse this symbol", ind, str);
                }
        }
    }

    private void number() throws ParseException {
        int startNumber = ind;
        StringBuilder curValue = intNumber();

        if (ind < str.length() && (symbol() == ',' || symbol() == '.')) {
            if (symbol() == ',') {
                curValue.append('.');
            } else {
                curValue.append(symbol());
            }
            ++ind;

            int len = curValue.length();

            curValue.append(intNumber());

            if (len == curValue.length()) {
                System.err.println("@@");
                throw new ParseException("Can't parse to int this string", startNumber, str);
            }
        }

        if (ind < str.length() && (symbol() == 'e' || symbol() == 'E')) {
            curValue.append(symbol());
            ++ind;

            int len = curValue.length();

            curValue.append(intNumber());

            if (len == curValue.length()) {
                throw new ParseException("Can't parse to int this string", startNumber, str);
            }
        }

        try {
            value = op.parse(curValue.toString());
        } catch (NumberFormatException e) {
            throw new ParseException("Can't parse to int this string", startNumber, curValue.toString());
        }
    }

    private StringBuilder intNumber() {
        StringBuilder ret = new StringBuilder();

        if (ind < str.length() && symbol() == '-') {
            ret.append('-');
            ++ind;
        } else if (ind < str.length() && symbol() == '+') {
            ret.append('+');
            ++ind;
        }

        while (ind < str.length() && Character.isDigit(symbol())) {
            ret.append(symbol());
            ++ind;
        }

        return ret;
    }

    private void Name() throws ParseException {
        StringBuilder curName = new StringBuilder();
        while (ind < str.length() && Character.isAlphabetic(symbol())) {
            curName.append(symbol());
            ++ind;
        }

        name = curName.toString();
    }

    private void skipSpaces() {
        while (ind < str.length() && Character.isWhitespace(str.charAt(ind))) {
            ++ind;
        }
    }

    public int getInd() {
        return ind;
    }

    public String getExpression() {
        return str;
    }

    public T getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
