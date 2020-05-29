package com.university.automaton;

public interface Transition {
    boolean isPossible(char c);
    State getState();
}
