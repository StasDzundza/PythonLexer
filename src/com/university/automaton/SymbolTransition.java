package com.university.automaton;

public class SymbolTransition implements Transition{
    private char symbol;
    private State state;

    public SymbolTransition(char symbol,State state){
        this.symbol = symbol;
        this.state = state;
    }

    @Override
    public boolean isPossible(char c) {
        return c == symbol;
    }

    @Override
    public State getState(){
        return state;
    }
}
