package com.university.main;

import com.university.lexer.Lexer;
import com.university.lexer.token.Token;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("There should be passed file");
        }
        Lexer lexer = new Lexer();
        try {
            List<Token> tokens = lexer.tokenize(args[0]);
            for (Token token : tokens) {
                System.out.println(token.toString());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
