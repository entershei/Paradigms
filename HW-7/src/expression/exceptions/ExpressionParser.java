package expression.exceptions;

import expression.*;

import java.util.Stack;

import expression.parser.Token;
import expression.parser.Tokenizer;

public class ExpressionParser implements Parser {
    private Stack<Number> constStack;
    private Stack<String> nameStack;
    private Stack<Token> postfixStack;
    private String expression;
    private Tokenizer tokenizer;

    @Override
    public TripleExpression parse(String expressionIn) throws ParseException {
        constStack = new Stack<>();
        nameStack = new Stack<>();
        postfixStack = new Stack<>();

        expression = expressionIn;
        tokenizer = new Tokenizer(expressionIn);
        postfix();

        return getExpression();
    }

    private TripleExpression getExpression() throws ParseException {
        if (postfixStack.empty()) {
            throw new ParseException("Operand expected", tokenizer.getInd(), expression);
        } else {
            Token token = postfixStack.pop();

            switch (token) {
                case NOT:
                    return new CheckedNegate(getExpression());
                case POW10:
                    return new CheckedPow10(getExpression());
                case LOG10:
                    return new CheckedLog10(getExpression());
                case CONST:
                    return new Const(constStack.pop());
                case VARIABLE:
                    return new Variable(nameStack.pop());
                default:
                    TripleExpression operand2 = getExpression();
                    TripleExpression operand1 = getExpression();

                    switch (token) {
                        case DIV:
                            return new CheckedDivide(operand1, operand2);
                        case MUL:
                            return new CheckedMultiply(operand1, operand2);
                        case ADD:
                            return new CheckedAdd(operand1, operand2);
                        case SUB:
                            return new CheckedSubtract(operand1, operand2);
                        default:
                            throw new ParseException("Token is wrong", tokenizer.getInd(), expression);
                    }
            }
        }
    }

    private void postfix() throws ParseException {
        Stack<Token> operationStack = new Stack<>();

        while (tokenizer.hasNextToken()) {
            Token token = tokenizer.nextToken();

            switch (token) {
                case CONST:
                    postfixStack.push(token);
                    constStack.push(tokenizer.getValue());
                    break;
                case VARIABLE:
                    postfixStack.push(token);
                    nameStack.push(tokenizer.getName());
                    break;
                case LEFT_PARENTHESIS:
                    operationStack.push(token);
                    break;
                case RIGHT_PARENTHESIS:
                    while (!operationStack.empty() && operationStack.peek() != Token.LEFT_PARENTHESIS) {
                        postfixStack.push(operationStack.pop());
                    }
                    if (operationStack.empty()) {
                        throw new ParseException("The opening parenthesis to this bracket is missing",
                                tokenizer.getInd() - 1, expression);
                    } else {
                        operationStack.pop();
                    }
                    break;
                default:
                    while (!operationStack.empty()
                            && operationStack.peek() != Token.LEFT_PARENTHESIS
                            && (token.getPriority(tokenizer) < operationStack.peek().getPriority(tokenizer)
                            || token.getPriority(tokenizer) == operationStack.peek().getPriority(tokenizer)
                            && !token.RightAssociative())) {
                        postfixStack.push(operationStack.pop());
                    }

                    operationStack.push(token);
                    break;
            }
        }

        while (!operationStack.empty()) {
            if (operationStack.peek() == Token.LEFT_PARENTHESIS) {
                throw new ParseException("Expected closing parenthesis");
            }
            postfixStack.push(operationStack.pop());
        }
    }
}
