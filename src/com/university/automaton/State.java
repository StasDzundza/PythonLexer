package com.university.automaton;

import java.util.HashMap;
import java.util.Map;

public class State {
    private Map<Character,State> transitions;
    private boolean isFinal;

    public State(boolean isFinal){
        transitions = new HashMap<>();
        this.isFinal = isFinal;
    }

    public void addTransition(char symbol, State state){
        transitions.put(symbol,state);
    }

    public boolean isPossibleTransitionBy(char symbol){
        return (transitions.get(symbol) != null)?true:false;
    }

    public State getNextStateByTransition(char symbol){
        return transitions.get(symbol);
    }

    public boolean isFinal(){
        return isFinal;
    }
}
