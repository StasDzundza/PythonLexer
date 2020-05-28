package com.university.automaton;

public class StateMachinesFactory {
    public static FiniteStateMachine operatorStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);//+
        State q2 = new State(true);//-
        State q3 = new State(true);//*
        State q4 = new State(true);//**
        State q5 = new State(true);// /

        q3.addTransition('*',q4);

        initial.addTransition('+',q1);
        initial.addTransition('-',q2);
        initial.addTransition('*',q3);
        initial.addTransition('/',q5);

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine newLineStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);// '\n'
        initial.addTransition('\n',q1);
        return new FiniteStateMachine(initial);
    }
}
