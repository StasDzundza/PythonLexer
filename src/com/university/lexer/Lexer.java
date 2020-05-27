package com.university.lexer;

import com.university.lexer.token.Token;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private List<Token> tokens;
    private List<String> sourceCode;

    public Lexer(){
        tokens = new ArrayList<>();
    }

    public List<Token> tokenize(String filepath) throws IOException {
        sourceCode.clear();
        tokens.clear();
        sourceCode = readFileData(filepath);

        return tokens;
    }

    private List<String> readFileData(String filepath) throws IOException {
        List<String> sourceCodeLines = Files.readAllLines(Paths.get(filepath), StandardCharsets.UTF_8);
        return sourceCodeLines;
    }
}
