package com.study;

import com.study.node.*;
import com.study.token.NumberToken;
import com.study.token.OperatorToken;
import com.study.token.Token;
import com.study.token.TokenType;
import org.apache.commons.collections4.iterators.PeekingIterator;

import java.util.List;

public class Parser {

    public Node parse(List<Token> tokens) {
        return parse(new PeekingIterator<>(tokens.iterator()));
    }

    /**
     * Parse a "Number Equivalent" node
     * <p>
     * We treat a number node and a parenthesis node as "Number Equivalent" node.
     * When a number node appears, we can always replace it with some equivalent parenthesis node.
     * So number node and parenthesis node are "equivalent" based on this observation.
     */
    private Node parseNumberEquivalent(PeekingIterator<Token> tokens) {
        if (!tokens.hasNext()) {
            throw new IllegalArgumentException("tokens have no more elements!");
        }

        Token token = tokens.peek();
        return switch (token.getType()) {
            case PARENTHESIS -> buildParenthesisNode(tokens);
            case NUMBER -> buildNumberNode(tokens);
            default -> throw new IllegalArgumentException(String.format("Unexpected TokenType: %s", token.getType()));
        };
    }

    private Node parse(PeekingIterator<Token> tokens) {
        if (!tokens.hasNext()) {
            throw new IllegalArgumentException("tokens have no more elements!");
        }

        Node previousNode = parseNumberEquivalent(tokens);
        while (true) {
            if (!tokens.hasNext()) {
                return previousNode;
            }

            Token peek = tokens.peek();
            // ')'
            if (peek.getType() == TokenType.PARENTHESIS) {
                return previousNode;
            }

            if (peek.getType() == TokenType.OPERATOR) {
                OperatorToken operatorToken = (OperatorToken) tokens.next();
                Node rightChild = parseNumberEquivalent(tokens);
                if (previousNode.getType() == NodeType.OPERATION) {
                    if (!(previousNode instanceof OperationNode)) {
                        throw new IllegalArgumentException();
                    }
                    previousNode = buildOperationNode((OperationNode) previousNode, operatorToken, rightChild);
                } else {
                    previousNode = new OperationNode(operatorToken, previousNode, rightChild);
                }
            }
        }
    }

    private OperationNode buildOperationNode(OperationNode previousNode, OperatorToken operatorToken, Node rightChild) {
        Operator currentOperator = operatorToken.operator();
        Operator previousOperator = previousNode.operatorToken().operator();

        int currentOperatorPriorityLevel = currentOperator.getOperatorPriority().getLevel();
        int previousOperatorPriorityLevel = previousOperator.getOperatorPriority().getLevel();

        if (currentOperatorPriorityLevel == previousOperatorPriorityLevel) {
            return new OperationNode(operatorToken, previousNode, rightChild);
        }

        if (currentOperatorPriorityLevel > previousOperatorPriorityLevel) {
            Node previousRightChild = previousNode.rightChild();
            Node modifiedRightChild = new OperationNode(operatorToken, previousRightChild, rightChild);
            return previousNode.copyWithSpecifiedRightChild(modifiedRightChild);
        }

        return new OperationNode(operatorToken, previousNode, rightChild);
    }

    private ParenthesisNode buildParenthesisNode(PeekingIterator<Token> tokens) {
        // drop '('
        tokens.next();
        Node innerNode = parse(tokens);
        // drop ')'
        tokens.next();
        return new ParenthesisNode(innerNode);
    }

    private NumberNode buildNumberNode(PeekingIterator<Token> tokens) {
        return new NumberNode((NumberToken) tokens.next());
    }
}
