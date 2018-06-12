package expression.parser;

import expression.*;

import java.util.NoSuchElementException;
import java.util.Stack;

public class ExpressionParser implements Parser {
    private Stack<Number> constStack;
    private Stack<String> nameStack;
    private Stack<Token> postfixStack;

    @Override
    public CommonExpression parse(String expression) {
        constStack = new Stack<>();
        nameStack = new Stack<>();
        postfixStack = new Stack<>();

        try {
            postfix(new Tokenizer(expression));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getExpression();
    }

    private CommonExpression getExpression() {
        if (postfixStack.empty()) {
            throw new IllegalStateException("not expression");
        } else {
            Token token = postfixStack.pop();

            switch (token) {
                case NOT:
                    return new Negate(getExpression());
                case CONST:
                    return new Const(constStack.pop());
                case VARIABLE:
                    return new Variable(nameStack.pop());
                default:
                    CommonExpression operand2 = getExpression();
                    CommonExpression operand1 = getExpression();

                    switch (token) {
                        case DIV:
                            return new Divide(operand1, operand2);
                        case MUL:
                            return new Multiply(operand1, operand2);
                        case ADD:
                            return new Add(operand1, operand2);
                        case SUB:
                            return new Subtract(operand1, operand2);
                        case AND:
                            return new And(operand1, operand2);
                        case XOR:
                            return new Xor(operand1, operand2);
                        case OR:
                            return new Or(operand1, operand2);
                        default:
                            throw new NoSuchElementException("token is wrong");
                    }
            }
        }
    }

    private void postfix(Tokenizer tokenizer) throws Exception {
        Stack<Token> operationStack = new Stack<>();

        while (tokenizer.hasNextToken()) {
            Token token = tokenizer.nextToken();

            switch (token) {
                case CONST:
                    postfixStack.push(token);
                    constStack.push(tokenizer.value);
                    break;
                case VARIABLE:
                    postfixStack.push(token);
                    nameStack.push(tokenizer.name);
                    break;
                case LEFT_PARENTHESIS:
                    operationStack.push(token);
                    break;
                case RIGHT_PARENTHESIS:
                    while (!operationStack.empty() && operationStack.peek() != Token.LEFT_PARENTHESIS) {
                        postfixStack.push(operationStack.pop());
                    }
                    if (operationStack.empty()) {
                        throw new Exception("can't parse");
                    } else {
                        operationStack.pop();
                    }
                    break;
                default:
                    while (!operationStack.empty()
                            && operationStack.peek() != Token.LEFT_PARENTHESIS
                            && (token.getPriority() < operationStack.peek().getPriority()
                            || token.getPriority() == operationStack.peek().getPriority()
                            && !token.RightAssociative())) {
                        postfixStack.push(operationStack.pop());
                    }

                    operationStack.push(token);
                    break;
            }
        }

        while (!operationStack.empty()) {
            postfixStack.push(operationStack.pop());
        }
    }
}
