package com.university.lexer;

import com.university.automaton.StateMachineFactory;
import com.university.lexer.token.TokenName;
import com.university.utils.Pair;

public class Patterns {
    public static final Pair[] patterns = new Pair[]{
            new Pair("#.*(\\r|\\n|\\r\\n|$)", TokenName.COMMENT),
            new Pair("int\\b", TokenName.DATA_TYPE),
            new Pair("float\\b", TokenName.DATA_TYPE),
            new Pair("str\\b", TokenName.DATA_TYPE),
            new Pair("complex\\b", TokenName.DATA_TYPE),
            new Pair("bool\\b", TokenName.DATA_TYPE),
            new Pair(StateMachineFactory.comparisonOperatorStateMachine(),TokenName.COMPARISON_OPERATOR),
            new Pair(StateMachineFactory.operatorStateMachine(),TokenName.OPERATOR),
            new Pair(StateMachineFactory.separatorStateMachine(),TokenName.SEPARATOR),
            new Pair(StateMachineFactory.bracketStateMachine(),TokenName.BRACKET),
            new Pair(StateMachineFactory.dotStateMachine(),TokenName.DOT),
            new Pair(StateMachineFactory.numberStateMachine(),TokenName.NUMBER),
            new Pair(StateMachineFactory.identifierStateMachine(),TokenName.IDENTIFIER)
    };
}
