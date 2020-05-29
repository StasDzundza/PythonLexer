package com.university.automaton;

public class FuncTransition implements Transition {
    private TransitionFunction transitionFunction;
    private State state;

    public FuncTransition(TransitionFunction transitionFunction, State state){
        this.transitionFunction = transitionFunction;
        this.state = state;
    }

    @Override
    public boolean isPossible(char c) {
        return transitionFunction.isPossibleToTransit(c);
    }

    @Override
    public State getState() {
        return state;
    }
}
