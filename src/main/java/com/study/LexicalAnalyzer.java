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

        // After ignoring whitespace codepoints, an operator always follows a number or a ')'
        boolean followedOperatorAllowed = false;

        while (peekingIterator.hasNext()) {
            int peek = peekingIterator.peek();
            if (whitespace(peek)) {
                // drop this whitespace codepoint
                peekingIterator.next();
                continue;
            }

            if (Parenthesis.qualified(peek)) {
                Parenthesis parenthesis = Parenthesis.build(peekingIterator.next());
                tokens.add(tokenBuilder.build(parenthesis));
                followedOperatorAllowed = (parenthesis == Parenthesis.RIGHT);
                continue;
            }

            if (followedOperatorAllowed && Operator.qualified(peek)) {
                Operator operator = Operator.build(peekingIterator.next());
                tokens.add(tokenBuilder.build(operator));
                followedOperatorAllowed = false;
                continue;
            }

            if (Number.qualifiedStartCodepoint(peek)) {
                Number number = numberBuilder.build(peekingIterator);
                tokens.add(tokenBuilder.build(number));
                followedOperatorAllowed = true;
                continue;
            }

            String message = String.format("Unexpected codepoint: [%s]", StringUtil.fromCodepoint(peek));
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
