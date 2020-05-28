package com.university.automaton;

import com.university.utils.Pair;

public class Automaton {
    private FiniteStateMachine finiteStateMachine;

    public Automaton(FiniteStateMachine finiteStateMachine) {
        this.finiteStateMachine = finiteStateMachine;
    }

    public Pair<Integer, Integer> match(String text, int fromPos) {
        finiteStateMachine.reset();
        int curPos = fromPos;
        while (finiteStateMachine.switchState(text.charAt(curPos)) != null) {
            curPos++;
        }
        if (finiteStateMachine.canStop()) {
            return new Pair<>(fromPos, curPos);
        } else {
            return new Pair<>(null, null);
        }
    }
}
