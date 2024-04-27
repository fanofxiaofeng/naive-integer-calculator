package com.study;

import com.study.node.Node;
import com.study.token.Token;
import org.apache.commons.collections4.iterators.PeekingIterator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        try (InputStream inputStream = System.in) {
            byte[] bytes = inputStream.readAllBytes();
            String raw = new String(bytes, StandardCharsets.UTF_8);

            LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
            List<Token> tokens = lexicalAnalyzer.analyze(new PeekingIterator<>(raw.codePoints().iterator()));

//            for (Token token : tokens) {
//                System.out.println(token);
//            }

            Parser parser = new Parser();
            Node node = parser.parse(tokens);
            Calculator calculator = new Calculator();
            BigInteger result = calculator.calculate(node);
            System.out.println(result);
        }
    }
}
