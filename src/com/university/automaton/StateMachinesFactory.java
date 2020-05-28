package com.university.automaton;

public class StateMachinesFactory {
    public static FiniteStateMachine operatorStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);// +
        State q2 = new State(true);// -
        State q3 = new State(true);// *
        State q4 = new State(true);// **
        State q5 = new State(true);// /
        State q6 = new State(true);// &
        State q7 = new State(true);// ^
        State q8 = new State(true);// |
        State q9 = new State(true);// =

        q1.addTransition('=',q9);q2.addTransition('=',q9);q3.addTransition('=',q9);
        q4.addTransition('=',q9);q5.addTransition('=',q9);q6.addTransition('=',q9);
        q7.addTransition('=',q9);q8.addTransition('=',q9);
        q3.addTransition('*',q4);

        initial.addTransition('+',q1);
        initial.addTransition('-',q2);
        initial.addTransition('*',q3);
        initial.addTransition('/',q5);
        initial.addTransition('&',q6);
        initial.addTransition('^',q7);
        initial.addTransition('|',q8);
        initial.addTransition('=',q9);

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine comparisonOperatorStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);// <
        State q2 = new State(true);// >
        State q3 = new State(true);// !
        State q4 = new State(false);// =
        State q5 = new State(true);// ==

        q1.addTransition('=',q4);
        q2.addTransition('=',q4);
        q3.addTransition('=',q4);
        q4.addTransition('=',q5);

        initial.addTransition('<',q1);
        initial.addTransition('>',q2);
        initial.addTransition('!',q3);
        initial.addTransition('=',q4);

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine bracketStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);// (
        State q2 = new State(true);// )
        State q3 = new State(true);// {
        State q4 = new State(true);// }
        State q5 = new State(true);// [
        State q6 = new State(true);// ]

        initial.addTransition('(',q1);
        initial.addTransition(')',q2);
        initial.addTransition('{',q3);
        initial.addTransition('}',q4);
        initial.addTransition('[',q5);
        initial.addTransition(']',q6);

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine separatorStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);// :
        State q2 = new State(true);// ,
        State q3 = new State(true);// ;

        initial.addTransition(':',q1);
        initial.addTransition(',',q2);
        initial.addTransition(';',q3);

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine newLineStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);// '\n'
        initial.addTransition('\n',q1);
        return new FiniteStateMachine(initial);
    }
}
