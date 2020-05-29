package com.university.automaton;

public class FiniteStateMachine{
    private State initialState;
    private State currentState;

    public FiniteStateMachine(State initialState){
        this.initialState = initialState;
        currentState = initialState;
    }

    public State switchState(char symbol){
        State nextState = currentState.getNextStateByTransition(symbol);
        if(nextState != null){
            currentState = nextState;
        }
        return nextState;
    }

    public boolean canStop(){
        return currentState.isFinal();
    }

    public void reset(){
        currentState = initialState;
    }
}
