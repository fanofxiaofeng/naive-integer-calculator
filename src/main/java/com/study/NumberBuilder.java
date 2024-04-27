package com.study;

import org.apache.commons.collections4.iterators.PeekingIterator;

import java.math.BigInteger;

public class NumberBuilder {
    public Number build(PeekingIterator<Integer> peekingIterator) {
        StringBuilder stringBuilder = new StringBuilder();
        while (peekingIterator.hasNext() && Number.qualified(peekingIterator.peek())) {
            stringBuilder.append(StringUtil.fromCodepoint(peekingIterator.next()));
        }
        BigInteger bigInteger = new BigInteger(stringBuilder.toString());
        return new Number(bigInteger);
    }
}
