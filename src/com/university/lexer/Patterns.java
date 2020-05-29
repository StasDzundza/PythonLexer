package com.university.lexer;

import com.university.automaton.StateMachinesFactory;
import com.university.lexer.token.TokenName;
import com.university.utils.Pair;

public class Patterns {
    public static final Pair[] patterns = new Pair[]{
            new Pair("#.*(\\r|\\n|\\r\\n|$)", TokenName.COMMENT),
            new Pair(StateMachinesFactory.operatorStateMachine(),TokenName.OPERATOR),
            new Pair(StateMachinesFactory.comparisonOperatorStateMachine(),TokenName.COMPARISON_OPERATOR),
            new Pair(StateMachinesFactory.separatorStateMachine(),TokenName.SEPARATOR),
            new Pair(StateMachinesFactory.bracketStateMachine(),TokenName.BRACKET),
            new Pair(StateMachinesFactory.dotStateMachine(),TokenName.DOT),
    };
}
