package expression.parser;

import expression.*;

import java.util.Stack;

import expression.exceptions.ParseException;
import expression.operations.*;

public class ExpressionParser<T> implements Parser<T> {
    private Stack<T> constStack;
    private Stack<String> nameStack;
    private Stack<Token> postfixStack;
    private String expression;
    private Tokenizer<T> tokenizer;
    private Operation<T> op;

    public ExpressionParser(Operation<T> opNew) {
        op = opNew;
    }

    @Override
    public TripleExpression<T> parse(String expressionIn) throws ParseException {
        constStack = new Stack<>();
        nameStack = new Stack<>();
        postfixStack = new Stack<>();

        expression = expressionIn;
        tokenizer = new Tokenizer<>(op, expressionIn);
        postfix();

        return getExpression();
    }

    private TripleExpression<T> getExpression() throws ParseException {
        if (postfixStack.empty()) {
            throw new ParseException("Operand expected", tokenizer.getInd(), expression);
        } else {
            Token token = postfixStack.pop();

            switch (token) {
                case NOT:
                    return new Negate<>(op, getExpression());
                case CONST:
                    return new Const<>(constStack.pop());
                case VARIABLE:
                    return new Variable<>(nameStack.pop());
                default:
                    TripleExpression<T> operand2 = getExpression();
                    TripleExpression<T> operand1 = getExpression();

                    switch (token) {
                        case DIV:
                            return new Divide<>(op, operand1, operand2);
                        case MUL:
                            return new Multiply<>(op, operand1, operand2);
                        case ADD:
                            return new Add<>(op, operand1, operand2);
                        case SUB:
                            return new Subtract<>(op, operand1, operand2);
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
