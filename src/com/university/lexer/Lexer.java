package com.university.lexer;

import com.university.automaton.Automaton;
import com.university.automaton.FiniteStateMachine;
import com.university.lexer.token.Location;
import com.university.lexer.token.Token;
import com.university.lexer.token.TokenName;
import com.university.utils.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private List<Token> tokens;
    private char END_OF_LINE = '$';

    public Lexer() {
        tokens = new ArrayList<>();
    }

    public List<Token> tokenize(String filepath) throws IOException {
        tokens.clear();
        int curLineNum = 1;
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line = reader.readLine();
        while (line != null) {
            StringBuffer buffer = new StringBuffer(line);
            String curLine = buffer.append(END_OF_LINE).toString();
            for (int i = 0; i < curLine.length(); ) {
                boolean isMatched = false;
                if(i == curLine.length() - 1){ //position at $ symbol
                    //replace all last $ symbols to NEWLINE token
                    tokens.add(new Token(TokenName.NEW_LINE,null,new Location(curLineNum,i)));
                    break;
                }
                for (Pair patternPair : Patterns.patterns) {
                    if (patternPair.getFirst() instanceof String) {
                        Pattern p = Pattern.compile((String) patternPair.getFirst(), Pattern.CASE_INSENSITIVE);
                        Matcher matcher = p.matcher(curLine);
                        if (matcher.find(i)) {
                            String matchedText = null;
                            if(patternPair.getSecond() != TokenName.NEW_LINE) {
                                matchedText = curLine.substring(matcher.start(), matcher.end());
                                if(matcher.end() == curLine.length()){
                                    matchedText = matchedText.substring(matcher.start(),matcher.end() - 1);
                                }
                            }
                            isMatched = true;
                            i = matcher.end() + 1;
                            Location begin = new Location(curLineNum, matcher.start());
                            tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, begin));
                            break;
                        }
                    } else {
                        Automaton automaton = new Automaton((FiniteStateMachine) patternPair.getFirst());
                        Pair<Integer, Integer> matchPos = automaton.match(curLine, i);
                        if (matchPos.getFirst() != null & matchPos.getSecond() != null) {
                            String matchedText = null;
                            if(patternPair.getSecond() != TokenName.NEW_LINE) {
                                matchedText = curLine.substring(matchPos.getFirst(), matchPos.getSecond());
                            }
                            i = matchPos.getSecond();
                            isMatched = true;
                            Location begin = new Location(curLineNum, matchPos.getFirst());
                            tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, begin));
                            break;
                        }
                    }
                }
                if (!isMatched) {
                    Location location = new Location(1, 1);
                    tokens.add(new Token(TokenName.ERROR_TOKEN, String.valueOf(curLine.charAt(i)), location));
                    ++i;
                }
            }
            line = reader.readLine();
            curLineNum++;
        }
        reader.close();
        return tokens;
    }
}
