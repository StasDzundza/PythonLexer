package com.university.automata;

import java.util.HashMap;
import java.util.Map;

public class State {
    private Map<String,State> transitions;
    private boolean isFinal = false;

    public State(boolean isFinal){
        transitions = new HashMap<>();
        this.isFinal = isFinal;
    }

    public void addTransition(String text, State state){
        transitions.put(text,state);
    }

    public boolean isPossibleTransitionBy(String text){
        return (transitions.get(text) != null)?true:false;
    }

    public State getNextStateByTransition(String text){
        return (State)transitions.get(text);
    }

    public boolean isFinal(){
        return isFinal;
    }
}
