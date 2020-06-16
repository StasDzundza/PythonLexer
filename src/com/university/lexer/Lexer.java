package com.university.lexer;

import com.university.automaton.Automaton;
import com.university.automaton.FiniteStateMachine;
import com.university.automaton.MultilineAutomaton;
import com.university.lexer.token.Location;
import com.university.lexer.token.Token;
import com.university.lexer.token.TokenName;
import com.university.utils.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private List<Token> tokens;

    private Stack<Integer> indentionLengths;

    public Lexer() {
        tokens = new ArrayList<>();
        indentionLengths = new Stack<>();
        indentionLengths.push(0);
    }

    public List<Token> tokenize(String filepath) throws IOException {
        tokens.clear();
        int curLineNum = 1;
        int multilineStringStartLine = -1;
        int multilineStringStartColumn = -1;
        StringBuffer multilineStringValue = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line = reader.readLine();
        while (line != null) {
            String curLine = line;
            for (int i = 0; i < curLine.length(); ) {
                boolean isMatched = false;
                for (Pair patternPair : Patterns.patterns) {
                    if (patternPair.getFirst() instanceof String) {
                        Pattern p = Pattern.compile((String) patternPair.getFirst(), Pattern.CASE_INSENSITIVE);
                        Matcher matcher = p.matcher(curLine);
                        if (matcher.find(i) && matcher.start() == i) {
                            String matchedText = curLine.substring(matcher.start(), matcher.end());
                            isMatched = true;
                            i = matcher.end();
                            Location location = new Location(curLineNum, matcher.start() + 1);
                            if (matcher.start() == 0) {
                                generateIndentionToken("", location);
                            }
                            tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, location));
                            break;
                        }
                    } else if (patternPair.getFirst() instanceof MultilineAutomaton) {
                        MultilineAutomaton multilineAutomaton = (MultilineAutomaton) patternPair.getFirst();
                        Pair<Integer, Integer> matchPos = multilineAutomaton.match(curLine, i);
                        if (matchPos.getSecond() != null) {
                            multilineStringValue.append(curLine, matchPos.getFirst(), matchPos.getSecond());
                            if (multilineStringStartLine == -1) {
                                multilineStringStartLine = curLineNum;
                            }
                            int whitespaceCharNum = calculateWhitespaceCharNumAtTheBeginning(curLine);
                            if(whitespaceCharNum == 0){
                                generateIndentionToken("",new Location(curLineNum,0));
                            }else{
                                generateIndentionToken(curLine.substring(0,whitespaceCharNum),new Location(curLineNum,0));
                            }
                            tokens.add(new Token(TokenName.MULTILINE_STRING, multilineStringValue.toString(),
                                    new Location(multilineStringStartLine, matchPos.getFirst() + 1)));
                            multilineStringValue = new StringBuffer();
                            multilineStringStartLine = multilineStringStartColumn = -1;
                            isMatched = true;
                            i = curLine.length();
                            break;
                        } else if (multilineAutomaton.started()) {
                            multilineStringValue.append(curLine, i, curLine.length());
                            if (multilineStringStartLine == -1) {
                                if(i == 0){
                                    generateIndentionToken("",new Location(curLineNum,0));
                                }
                                multilineStringStartLine = curLineNum;
                                multilineStringStartColumn = i + 1;
                            }
                            int whitespaceCharNum = calculateWhitespaceCharNumAtTheBeginning(curLine);
                            if(whitespaceCharNum == 0){
                                generateIndentionToken("",new Location(curLineNum,0));
                            }else{
                                generateIndentionToken(curLine.substring(0,whitespaceCharNum),new Location(curLineNum,0));
                            }
                            isMatched = true;
                            i = curLine.length();
                            break;
                        }
                    } else {
                        Automaton automaton = new Automaton((FiniteStateMachine) patternPair.getFirst());
                        Pair<Integer, Integer> matchPos = automaton.match(curLine, i);
                        if (matchPos.getFirst() != null & matchPos.getSecond() != null) {
                            String matchedText = curLine.substring(matchPos.getFirst(), matchPos.getSecond());
                            i = matchPos.getSecond();
                            isMatched = true;
                            Location location = new Location(curLineNum, matchPos.getFirst() + 1);
                            if (patternPair.getSecond() == TokenName.IDENTIFIER) {
                                if(matchPos.getFirst() == 0){
                                    generateIndentionToken("", location);
                                }
                                if (Patterns.isKeyword(matchedText)) {
                                    tokens.add(new Token(TokenName.KEYWORD, matchedText, location));
                                } else if (Patterns.isDataType(matchedText)) {
                                    tokens.add(new Token(TokenName.DATA_TYPE, matchedText, location));
                                } else {
                                    tokens.add(new Token(TokenName.IDENTIFIER, matchedText, location));
                                }
                            } else if (patternPair.getSecond() == TokenName.WHITESPACE && matchPos.getFirst() == 0) {
                                generateIndentionToken(matchedText, location);
                            } else if (patternPair.getSecond() != TokenName.WHITESPACE && matchPos.getFirst() == 0) {
                                generateIndentionToken("", location);
                                tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, location));
                            }else {
                                tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, location));
                            }
                            break;
                        }
                    }
                }
                if (!isMatched) {
                    Location location = new Location(curLineNum, i + 1);
                    if (i == 0) {
                        generateIndentionToken("", location);
                    }
                    tokens.add(new Token(TokenName.ERROR_TOKEN, String.valueOf(curLine.charAt(i)), location));
                    ++i;
                }
            }
            line = reader.readLine();
            curLineNum++;
        }

        if (((MultilineAutomaton) Patterns.patterns[0].getFirst()).started()) {
            tokens.add(new Token(TokenName.NOT_ENDED_MULTILINE_STRING_ERROR,
                    multilineStringValue.toString(), new Location(multilineStringStartLine, multilineStringStartColumn)));
        }

        while (!indentionLengths.empty()) {
            tokens.add(new Token(TokenName.DEDENT, null, new Location(curLineNum, 0)));
            indentionLengths.pop();
        }

        reader.close();
        return tokens;
    }

    private void generateIndentionToken(String whitespaceString, Location location) {
        StringBuffer spacesBuffer = new StringBuffer();
        for (int i = 0; i < whitespaceString.length(); ++i) {
            char c = whitespaceString.charAt(i);
            if (c == ' ') {
                spacesBuffer.append(whitespaceString.charAt(i));
            } else if (c == '\t') {
                do {
                    spacesBuffer.append(' ');
                } while (spacesBuffer.length() % 8 != 0);
            }
        }

        String spaces = spacesBuffer.toString();
        int topStackIndentLength = indentionLengths.peek();

        if (spaces.length() > topStackIndentLength) {
            indentionLengths.push(spaces.length());
            tokens.add(new Token(TokenName.INDENT, spaces, location));
        } else if (spaces.length() < topStackIndentLength) {
            while (topStackIndentLength > spaces.length()) {
                tokens.add(new Token(TokenName.DEDENT, null, location));
                indentionLengths.pop();
                topStackIndentLength = indentionLengths.peek();
            }
        }
    }

    private int calculateWhitespaceCharNumAtTheBeginning(String text){
        int spaceNum = 0;
        for (int i = 0; i < text.length(); i++){
            if(text.charAt(i) == ' ' || text.charAt(i) == '\t'){
                ++spaceNum;
            }
        }
        return spaceNum;
    }
}
