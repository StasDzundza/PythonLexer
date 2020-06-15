package com.university.automaton;

import com.university.lexer.Patterns;

import java.util.Arrays;

public class StateMachineFactory {
    public static FiniteStateMachine operatorStateMachine() {
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
        State q10 = new State(true);// //
        State q11 = new State(true);// ~
        State q12 = new State(false);// <
        State q13 = new State(true);// <<
        State q14 = new State(false);// >
        State q15 = new State(true);// >>
        State q16 = new State(true);// %

        q3.addTransition(new SymbolTransition('*', q4));// * to **
        q5.addTransition(new SymbolTransition('/', q10));// / to //
        q12.addTransition(new SymbolTransition('<', q13)); //< to <<
        q14.addTransition(new SymbolTransition('>', q15));// > to >>

        //all to =
        q1.addTransition(new SymbolTransition('=', q9));
        q2.addTransition(new SymbolTransition('=', q9));
        q3.addTransition(new SymbolTransition('=', q9));
        q4.addTransition(new SymbolTransition('=', q9));
        q5.addTransition(new SymbolTransition('=', q9));
        q6.addTransition(new SymbolTransition('=', q9));
        q7.addTransition(new SymbolTransition('=', q9));
        q8.addTransition(new SymbolTransition('=', q9));
        q10.addTransition(new SymbolTransition('=', q9));
        q13.addTransition(new SymbolTransition('=', q9));
        q15.addTransition(new SymbolTransition('=', q9));
        q16.addTransition(new SymbolTransition('=', q9));

        initial.addTransition(new SymbolTransition('+', q1));
        initial.addTransition(new SymbolTransition('-', q2));
        initial.addTransition(new SymbolTransition('*', q3));
        initial.addTransition(new SymbolTransition('/', q5));
        initial.addTransition(new SymbolTransition('&', q6));
        initial.addTransition(new SymbolTransition('^', q7));
        initial.addTransition(new SymbolTransition('|', q8));
        initial.addTransition(new SymbolTransition('=', q9));
        initial.addTransition(new SymbolTransition('~', q11));
        initial.addTransition(new SymbolTransition('<', q12));
        initial.addTransition(new SymbolTransition('>', q14));
        initial.addTransition(new SymbolTransition('%', q16));

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine comparisonOperatorStateMachine() {
        State initial = new State(false);
        State q1 = new State(true);// <
        State q2 = new State(true);// >
        State q3 = new State(false);// !
        State q4 = new State(true);// <=, >=, ==,!=
        State q5 = new State(true);// ==
        State q6 = new State(false); // =

        q1.addTransition(new SymbolTransition('=', q4)); // < to <=
        q2.addTransition(new SymbolTransition('=', q4)); // > to >=
        q3.addTransition(new SymbolTransition('=', q4)); // ! to !=
        q6.addTransition(new SymbolTransition('=', q5)); // = to ==

        initial.addTransition(new SymbolTransition('<', q1));
        initial.addTransition(new SymbolTransition('>', q2));
        initial.addTransition(new SymbolTransition('!', q3));
        initial.addTransition(new SymbolTransition('=', q6));

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine bracketStateMachine() {
        State initial = new State(false);
        State q1 = new State(true);// (
        State q2 = new State(true);// )
        State q3 = new State(true);// {
        State q4 = new State(true);// }
        State q5 = new State(true);// [
        State q6 = new State(true);// ]

        initial.addTransition(new SymbolTransition('(', q1));
        initial.addTransition(new SymbolTransition(')', q2));
        initial.addTransition(new SymbolTransition('{', q3));
        initial.addTransition(new SymbolTransition('}', q4));
        initial.addTransition(new SymbolTransition('[', q5));
        initial.addTransition(new SymbolTransition(']', q6));

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine separatorStateMachine() {
        State initial = new State(false);
        State q1 = new State(true);// :
        State q2 = new State(true);// ,
        State q3 = new State(true);// ;

        initial.addTransition(new SymbolTransition(':', q1));
        initial.addTransition(new SymbolTransition(',', q2));
        initial.addTransition(new SymbolTransition(';', q3));

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine dotStateMachine() {
        State initial = new State(false);
        State q1 = new State(true);// .
        initial.addTransition(new SymbolTransition('.', q1));
        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine identifierStateMachine() {
        State initial = new State(false);
        State q1 = new State(true);
        TransitionFunction startNameTransition = (c) -> c == '_' || Character.isLetter(c);
        initial.addTransition(new FuncTransition(startNameTransition, q1));
        TransitionFunction nameTransition = (c) -> c == '_' || Character.isLetter(c) || Character.isDigit(c);
        q1.addTransition(new FuncTransition(nameTransition, q1));
        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine numberStateMachine() {
        State initial = new State(false);
        State q1 = new State(true);
        TransitionFunction transitionFunction = (c) -> Character.isDigit(c);
        initial.addTransition(new FuncTransition(transitionFunction, q1));
        q1.addTransition(new FuncTransition(transitionFunction, q1));
        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine doubleQuoteStringStateMachine() {
        State initial = new State(false);
        State q1 = new State(false);
        State q2 = new State(true);
        State q3 = new State(false);
        TransitionFunction strSymbols = (c) -> c != '\"' && c != '\\';
        TransitionFunction notSlash = (c) -> c!='\\';

        q1.addTransition(new FuncTransition(strSymbols, q1));
        q1.addTransition(new SymbolTransition('\\',q3));
        q3.addTransition(new SymbolTransition('\\',q3));
        q3.addTransition(new FuncTransition(notSlash,q1));
        q1.addTransition(new SymbolTransition('\"', q2)); //end string
        initial.addTransition(new SymbolTransition('\"', q1)); // start string

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine singleQuoteStringStateMachine() {
        State initial = new State(false);
        State q1 = new State(false);
        State q2 = new State(true);
        State q3 = new State(false);
        TransitionFunction strSymbols = (c) -> c != '\'' && c != '\\';
        TransitionFunction notSlash = (c) -> c!='\\';

        q1.addTransition(new FuncTransition(strSymbols, q1));
        q1.addTransition(new SymbolTransition('\\',q3));
        q3.addTransition(new SymbolTransition('\\',q3));
        q3.addTransition(new FuncTransition(notSlash,q1));
        q1.addTransition(new SymbolTransition('\'', q2)); //end string
        initial.addTransition(new SymbolTransition('\'', q1)); // start string

        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine whitespaceStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);
        TransitionFunction funcTransition = (c) -> c == ' ' || c == '\t';
        q1.addTransition(new FuncTransition(funcTransition,q1));
        initial.addTransition(new FuncTransition(funcTransition,q1));
        return new FiniteStateMachine(initial);
    }

    public static FiniteStateMachine newLineStateMachine(){
        State initial = new State(false);
        State q1 = new State(true);
        initial.addTransition(new SymbolTransition('\n',q1));
        return new FiniteStateMachine(initial);
    }
}
