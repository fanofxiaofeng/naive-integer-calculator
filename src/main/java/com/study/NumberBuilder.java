package com.study;

import org.apache.commons.collections4.iterators.PeekingIterator;

import java.math.BigInteger;

public class NumberBuilder {
    public Number build(PeekingIterator<Integer> peekingIterator) {
        StringBuilder stringBuilder = new StringBuilder();
        if (peekingIterator.hasNext() && peekingIterator.peek() == Number.NEGATIVE_SYMBOL) {
            stringBuilder.append(StringUtil.fromCodepoint(peekingIterator.next()));
        }

        while (peekingIterator.hasNext() && Number.qualifiedDigit(peekingIterator.peek())) {
            stringBuilder.append(StringUtil.fromCodepoint(peekingIterator.next()));
        }

        BigInteger bigInteger = new BigInteger(stringBuilder.toString());
        return new Number(bigInteger);
    }
}
