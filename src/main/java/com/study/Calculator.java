package com.study;

import com.study.node.Node;
import com.study.node.NumberNode;
import com.study.node.OperationNode;
import com.study.node.ParenthesisNode;

import java.math.BigInteger;

public class Calculator {
    public BigInteger calculate(Node node) {
        if (node instanceof NumberNode numberNode) {
            return numberNode.numberToken().number().bigInteger();
        }
        if (node instanceof ParenthesisNode parenthesisNode) {
            return calculate(parenthesisNode.node());
        }
        if (node instanceof OperationNode operationNode) {
            BigInteger left = calculate(operationNode.leftChild());
            BigInteger right = calculate(operationNode.rightChild());
            return switch (operationNode.operatorToken().operator()) {
                case PLUS -> left.add(right);
                case MINUS -> left.subtract(right);
                case MULTIPLY -> left.multiply(right);
                case DIVIDE -> left.divide(right);
            };
        }
        throw new IllegalArgumentException("Unsupported node");
    }
}
