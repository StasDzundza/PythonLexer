package com.university.automaton;

public class FiniteStateMachine{
    private State initialState;
    private State currentState;

    public FiniteStateMachine(State initialState){
        this.initialState = initialState;
        currentState = initialState;
    }

    public State switchState(char symbol){
        if(currentState.isPossibleTransitionBy(symbol)){
            currentState = currentState.getNextStateByTransition(symbol);
            return currentState;
        }
        return null;
    }

    public boolean canStop(){
        return currentState.isFinal();
    }

    public void reset(){
        currentState = initialState;
    }
}
