package com.study;

import java.util.ArrayList;
import java.util.List;

import com.study.token.Token;
import org.apache.commons.collections4.iterators.PeekingIterator;

public class LexicalAnalyzer {

    private final TokenBuilder tokenBuilder = new TokenBuilder();
    private final NumberBuilder numberBuilder = new NumberBuilder();

    public List<Token> analyze(PeekingIterator<Integer> peekingIterator) {
        List<Token> tokens = new ArrayList<>();
        while (peekingIterator.hasNext()) {
            int peek = peekingIterator.peek();
            if (whitespace(peek)) {
                // drop this codepoint
                peekingIterator.next();
                continue;
            }
            if (Parenthesis.qualified(peek)) {
                Parenthesis parenthesis = Parenthesis.build(peekingIterator.next());
                tokens.add(tokenBuilder.build(parenthesis));
                continue;
            }

            if (Operator.qualified(peek)) {
                Operator operator = Operator.build(peekingIterator.next());
                tokens.add(tokenBuilder.build(operator));
                continue;
            }

            if (Number.qualified(peek)) {
                Number number = numberBuilder.build(peekingIterator);
                tokens.add(tokenBuilder.build(number));
                continue;
            }

            String message = String.format("Unexpected codepoint: %s", StringUtil.fromCodepoint(peek));
            throw new IllegalArgumentException(message);
        }

        return tokens;
    }

    private boolean whitespace(int codepoint) {
        return switch (codepoint) {
            case ' ', '\t', '\n', '\r' -> true;
            default -> false;
        };
    }
}
