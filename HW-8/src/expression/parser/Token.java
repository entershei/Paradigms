package expression.parser;

import expression.exceptions.ParseException;

public enum Token {
    CONST, VARIABLE, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, NOT, MUL, DIV, ADD, SUB, LOG10, POW10;

    public boolean RightAssociative() {
        switch (this) {
            case NOT:
                return true;
            case LOG10:
                return true;
            case POW10:
                return true;
            default:
                return false;
        }
    }

    public int getPriority(Tokenizer tokenizer) throws ParseException {
        switch (this) {
            case CONST:
                return 11;
            case VARIABLE:
                return 11;
            case LEFT_PARENTHESIS:
                return 9;
            case RIGHT_PARENTHESIS:
                return 9;
            case LOG10:
                return 7;
            case POW10:
                return 7;
            case NOT:
                return 7;
            case MUL:
                return 5;
            case DIV:
                return 5;
            case ADD:
                return 3;
            case SUB:
                return 3;
            default:
                throw new ParseException("Wrong enter " + this, tokenizer.getInd(), tokenizer.getExpression());
        }
    }
}