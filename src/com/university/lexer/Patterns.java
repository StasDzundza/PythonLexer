package com.university.lexer;

import com.university.automaton.MultilineAutomaton;
import com.university.automaton.StateMachineFactory;
import com.university.lexer.token.TokenName;
import com.university.utils.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Patterns {
    public static final Pair[] patterns = new Pair[]{
            new Pair(new MultilineAutomaton(), TokenName.MULTILINE_STRING),
            new Pair(StateMachineFactory.whitespaceStateMachine(), TokenName.WHITESPACE),
            new Pair("#.*(\\r|\\n|\\r\\n|$)", TokenName.COMMENT),
            new Pair(StateMachineFactory.comparisonOperatorStateMachine(), TokenName.COMPARISON_OPERATOR),
            new Pair(StateMachineFactory.operatorStateMachine(), TokenName.OPERATOR),
            new Pair(StateMachineFactory.separatorStateMachine(), TokenName.SEPARATOR),
            new Pair(StateMachineFactory.bracketStateMachine(), TokenName.BRACKET),
            new Pair(StateMachineFactory.dotStateMachine(), TokenName.DOT),
            new Pair(StateMachineFactory.numberStateMachine(), TokenName.NUMBER),
            new Pair(StateMachineFactory.identifierStateMachine(), TokenName.IDENTIFIER),
            new Pair(StateMachineFactory.doubleQuoteStringStateMachine(), TokenName.STRING),
            new Pair(StateMachineFactory.singleQuoteStringStateMachine(), TokenName.STRING),
            new Pair(StateMachineFactory.newLineStateMachine(), TokenName.NEW_LINE)
    };

    private static final String[] KEYWORDS_VALUES = {"import","as","class","def","pass","if","elif","else","try","except","throw",
        "for","while","in","is","await","None","raise","True","False","return","and","or","lambda","break","continue","from",
        "assert","with","not","async","yield","global","del"};

    private static final String[] DATA_TYPE_VALUES = {"int","str","bool","float","complex"};

    private static Set<String> KEYWORDS = new HashSet<>(Arrays.asList(KEYWORDS_VALUES));

    private static Set<String> DATA_TYPES = new HashSet<>(Arrays.asList(DATA_TYPE_VALUES));

    public static boolean isKeyword(String identifier){
        return KEYWORDS.contains(identifier);
    }

    public static boolean isDataType(String identifier){
        return DATA_TYPES.contains(identifier);
    }
}
