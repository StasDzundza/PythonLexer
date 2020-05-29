package com.university.main;

import com.university.lexer.Lexer;
import com.university.lexer.token.Token;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Test {
    public static void main(String[] args) {
        String line = "klmabcnop";
        System.out.println(line.substring(0,1));
        Pattern p = Pattern.compile("abc", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(line);
        if(matcher.find(0)){
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println(line.substring(matcher.start(),matcher.end()));
        }
    }
}

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
