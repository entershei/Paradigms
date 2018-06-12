package expression.parser;

import java.text.NumberFormat;

public class Tokenizer {
    final private String str;
    private int ind;
    public String name;
    public Number value;
    boolean canUnary = true;

    char symb() {
        return str.charAt(ind);
    }

    Tokenizer(String str) {
        this.str = str;
    }

    public boolean hasNextToken() {
        skipSpaces();
        return ind < str.length();
    }

    public Token nextToken() throws Exception {
        if (!hasNextToken()) {
            throw new Exception("haven't token");
        }

        skipSpaces();

        switch (symb()) {
            case '+':
                ++ind;
                canUnary = true;
                return Token.ADD;
            case '-':
                ++ind;
                if (canUnary) {
                    return Token.NOT;
                } else {
                    canUnary = true;
                    return Token.SUB;
                }
            case '*':
                ++ind;
                canUnary = true;
                return Token.MUL;
            case '/':
                ++ind;
                canUnary = true;
                return Token.DIV;
            case '(':
                ++ind;
                canUnary = true;
                return Token.LEFT_PARENTHESIS;
            case ')':
                ++ind;
                canUnary = false;
                return Token.RIGHT_PARENTHESIS;
            case '&':
                ++ind;
                canUnary = true;
                return Token.AND;
            case '^':
                ++ind;
                canUnary = true;
                return Token.XOR;
            case '|':
                ++ind;
                canUnary = true;
                return Token.OR;
            default:
                canUnary = false;

                if (Character.isAlphabetic(symb()))  {
                    StringBuilder curName = new StringBuilder();
                    while (ind < str.length() && Character.isAlphabetic(symb())) {
                        curName.append(symb());
                        ++ind;
                    }

                    name = curName.toString();
                    return Token.VARIABLE;
                } else if (Character.isDigit(symb())) {
                    StringBuilder curValue = new StringBuilder();

                    while (ind < str.length() && Character.isDigit(symb())) {
                        curValue.append(symb());
                        ++ind;
                    }

                    value = NumberFormat.getInstance().parse(curValue.toString());
                    return Token.CONST;
                } else {
                    throw new Exception("can't parse " + symb());
                }
        }
    }

    private void skipSpaces() {
        while (ind < str.length() && Character.isWhitespace(str.charAt(ind))) {
            ++ind;
        }
    }
}
