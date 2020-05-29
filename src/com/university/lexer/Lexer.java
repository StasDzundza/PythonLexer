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

    public Lexer() {
        tokens = new ArrayList<>();
    }

    public List<Token> tokenize(String filepath) throws IOException {
        tokens.clear();
        int curLineNum = 1;
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
                            i = matcher.end() + 1;
                            Location begin = new Location(curLineNum, matcher.start() + 1);
                            tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, begin));
                            break;
                        }
                    } else {
                        Automaton automaton = new Automaton((FiniteStateMachine) patternPair.getFirst());
                        Pair<Integer, Integer> matchPos = automaton.match(curLine, i);
                        if (matchPos.getFirst() != null & matchPos.getSecond() != null) {
                            String matchedText = curLine.substring(matchPos.getFirst(), matchPos.getSecond());
                            i = matchPos.getSecond();
                            isMatched = true;
                            Location begin = new Location(curLineNum, matchPos.getFirst() + 1);
                            tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, begin));
                            break;
                        }
                    }
                }
                if (!isMatched) {
                    Location location = new Location(curLineNum, i+1);
                    tokens.add(new Token(TokenName.ERROR_TOKEN, String.valueOf(curLine.charAt(i)), location));
                    ++i;
                }
            }
            tokens.add(new Token(TokenName.NEW_LINE,null,new Location(curLineNum,line.length())));
            line = reader.readLine();
            curLineNum++;
        }
        reader.close();
        return tokens;
    }
}
