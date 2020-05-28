package com.university.lexer;

import com.university.automaton.StateMachinesFactory;
import com.university.lexer.token.TokenName;
import com.university.utils.Pair;

public class Patterns {
    public static final Pair[] patterns = new Pair[]{
            new Pair("#.*(\\r|\\n|\\r\\n|$)", TokenName.COMMENT),
            new Pair(StateMachinesFactory.operatorStateMachine(),TokenName.KEYWORD),
            new Pair(StateMachinesFactory.newLineStateMachine(),TokenName.NEW_LINE)
    };
}
