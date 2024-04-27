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
        return doParse(new PeekingIterator<>(tokens.iterator()), true);
    }

    private Node doParse(PeekingIterator<Token> tokens, boolean operatorAware) {
        if (!tokens.hasNext()) {
            throw new IllegalArgumentException("tokens have no more elements!");
        }

        Token token = tokens.peek();
        Node numberEquivalent = switch (token.getType()) {
            case PARENTHESIS -> buildParenthesisNode(tokens);
            case NUMBER -> buildNumberNode(tokens);
            default -> throw new IllegalArgumentException(String.format("Unexpected TokenType: %s", token.getType()));
        };

        if (!operatorAware) {
            return numberEquivalent;
        }

        Node previousNode = numberEquivalent;
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
                Operator currentOperator = operatorToken.operator();
                Node rightChild = doParse(tokens, false);
                if (previousNode.getType() == NodeType.OPERATION) {
                    if (!(previousNode instanceof OperationNode)) {
                        throw new IllegalArgumentException();
                    }

                    Operator previousOperator = ((OperationNode) previousNode).operatorToken().operator();
                    if (currentOperator.getOperatorPriority().getLevel() == previousOperator.getOperatorPriority().getLevel()) {
                        previousNode = new OperationNode(operatorToken, previousNode, rightChild);
                        continue;
                    }
                    if (currentOperator.getOperatorPriority().getLevel() > previousOperator.getOperatorPriority().getLevel()) {
                        Node previousRightChild = ((OperationNode) previousNode).rightChild();
                        previousNode = ((OperationNode) previousNode).copyWithSpecifiedRightChild(
                                new OperationNode(operatorToken, previousRightChild, rightChild)
                        );
                        continue;
                    }
                }
                previousNode = new OperationNode(operatorToken, previousNode, rightChild);
            }
        }
    }

    private ParenthesisNode buildParenthesisNode(PeekingIterator<Token> tokens) {
        // drop '('
        tokens.next();
        Node innerNode = doParse(tokens, true);
        // drop ')'
        tokens.next();
        return new ParenthesisNode(innerNode);
    }

    private NumberNode buildNumberNode(PeekingIterator<Token> tokens) {
        return new NumberNode((NumberToken) tokens.next());
    }
}
