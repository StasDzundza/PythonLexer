package com.university.automaton;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<Transition> transitions;
    private boolean isFinal;

    public State(boolean isFinal){
        transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    public void addTransition(Transition transition){
        transitions.add(transition);
    }

    public State getNextStateByTransition(char symbol){
        for(Transition transition : transitions){
            if(transition.isPossible(symbol)){
                return transition.getState();
            }
        }
        return null;
    }

    public boolean isFinal(){
        return isFinal;
    }
}
