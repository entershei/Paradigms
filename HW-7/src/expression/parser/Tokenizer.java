package expression.parser;

import expression.exceptions.NotFoundOperandException;
import expression.exceptions.ParseException;

public class Tokenizer {
    final private String str;
    private int ind;
    private String name;
    private int value;
    private boolean canUnary = true;
    private boolean CanNotBinaryOperation = true;

    char symb() {
        return str.charAt(ind);
    }

    char nextSymb() {
        skipSpaces();
        return str.charAt(ind + 1);
    }

    boolean hasNextSymb() {
        return ind + 1 < str.length();
    }

    public Tokenizer(String str) {
        this.str = str;
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
        if (hasNextSymb()) {
            nextSymbol = nextSymb();
        }

        skipSpaces();

        String curName = "";

        switch (symb()) {
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
                        Number();

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

                    if (!Character.isDigit(symb()) && !Character.isAlphabetic(symb()) && symb() != '('
                            && symb() != '-') {
                        throw new ParseException("Not found second operand after - ", ind, str);
                    }

                    return Token.SUB;
                }
            case 'p':
                CanNotBinaryOperation = false;
                Name();
                Number();

                curName = name + Integer.toString(value);

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
                Number();

                curName = name + Integer.toString(value);

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

                if (Character.isAlphabetic(symb())) {
                    int startName = ind;

                    Name();

                    if (!name.equals("x") && !name.equals("y") && !name.equals("z")) {
                        throw new ParseException("Variable should be x, y or z; Found " + name, startName, str);
                    }
                    return Token.VARIABLE;
                } else if (Character.isDigit(symb())) {
                    Number();

                    return Token.CONST;
                } else {
                    throw new ParseException("Can't parse this symbol", ind, str);
                }
        }
    }

    private void Number() throws ParseException {
        StringBuilder curValue = new StringBuilder();
        int startNumber = ind;

        if (symb() == '-') {
            curValue.append('-');
            ++ind;
        }

        while (ind < str.length() && Character.isDigit(symb())) {
            curValue.append(symb());
            ++ind;
        }

        try {
            value = Integer.parseInt(curValue.toString());
        } catch (NumberFormatException e) {
            throw new ParseException("Can't parse to int this string", startNumber, str);
        }
    }

    private void Name() throws ParseException {
        StringBuilder curName = new StringBuilder();
        while (ind < str.length() && Character.isAlphabetic(symb())) {
            curName.append(symb());
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

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
