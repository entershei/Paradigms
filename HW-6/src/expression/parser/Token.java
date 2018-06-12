package expression.parser;

import java.text.ParseException;

enum Token {
    CONST, VARIABLE, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, NOT, MUL, DIV, ADD, SUB, AND, XOR, OR;

    boolean RightAssociative() {
        switch (this) {
            case NOT:
                return true;
            default:
                return false;
        }
    }

    int getPriority() throws Exception {
        switch (this) {
            case CONST:
                return 11;
            case VARIABLE:
                return 11;
            case LEFT_PARENTHESIS:
                return 9;
            case RIGHT_PARENTHESIS:
                return 9;
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
            case AND:
                return 2;
            case XOR:
                return 1;
            case OR:
                return 0;
            default:
                throw new Exception("wrong enter " + this);
        }
    }
}