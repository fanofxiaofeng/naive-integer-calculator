package com.study.node;

public sealed interface Node
        permits NumberNode,
        ParenthesisNode,
        OperationNode {

    NodeType getType();
}
