package com.university.automaton;

import com.university.utils.Pair;

public class MultilineAutomaton {
    private int state = 0;
    private int startPos;

    public MultilineAutomaton() {
    }

    public Pair<Integer, Integer> match(String text, int fromPos) {
        if (text.length() - fromPos < 3 || (state > 0 && fromPos > 0 && text.charAt(fromPos-1) == '\\'))
            return new Pair<>(null, null);
        int nextState = -1;
        if (text.startsWith("\'\'\'",fromPos)){
            nextState = 1;
        }else if(text.startsWith("\"\"\"",fromPos)){
            nextState = 2;
        }

        if (nextState == state) {
            state = 0;
            return new Pair<>(startPos, fromPos + 2);
        } else if (nextState > 0) {
            int endPos = -1;
            if (nextState == 1) {
                endPos = text.indexOf("\'\'\'", fromPos + 3);
            } else {
                endPos = text.indexOf("\"\"\"", fromPos + 3);
            }
            if (endPos != -1) {
                state = 0;
                return new Pair<>(fromPos, endPos + 2);
            } else {
                state = nextState; startPos = fromPos;
                return new Pair<>(null, null);
            }
        } else {
            return new Pair<>(null, null);
        }
    }

    public boolean started() {
        return state > 0;
    }
}
