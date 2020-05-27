package com.university.automata;

public class FiniteStateMachine {
    private State currentState;

    public FiniteStateMachine(State initialState){
        currentState = initialState;
    }

    public State switchState(String text){
        if(currentState.isPossibleTransitionBy(text)){
            currentState = currentState.getNextStateByTransition(text);
            return currentState;
        }
        return null;
    }

    public boolean canStop(){
        return currentState.isFinal();
    }

}
