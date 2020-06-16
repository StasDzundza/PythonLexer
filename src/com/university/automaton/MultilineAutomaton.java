package com.university.automaton;

import com.university.utils.Pair;

public class MultilineAutomaton {
    private int state = 0;
    private int startPos;

    public MultilineAutomaton() {
    }

    public Pair<Integer, Integer> match(String text, int fromPos) {
        if (text.length() - fromPos < 3 || (state > 0 && fromPos > 0 && text.charAt(fromPos - 1) == '\\'))
            return new Pair<>(null, null);
        int nextState = -1;
        int endPos = -1;
        boolean startedNow = false;
        if (state == 0 && text.startsWith("\'\'\'", fromPos)) {
            startedNow = true;
            nextState = 1;
            int findFrom = fromPos + 3;
            do {
                endPos = text.indexOf("\'\'\'", findFrom);
                if (endPos != -1) {
                    if (text.charAt(endPos - 1) != '\\') {
                        return new Pair<>(fromPos, endPos + 3);
                    } else {
                        ++findFrom;
                    }
                }
            } while (endPos != -1);
        } else if (state == 0 && text.startsWith("\"\"\"", fromPos)) {
            startedNow = true;
            nextState = 2;
            int findFrom = fromPos + 3;
            do {
                endPos = text.indexOf("\"\"\"", findFrom);
                if (endPos != -1) {
                    if (text.charAt(endPos - 1) != '\\') {
                        return new Pair<>(fromPos, endPos + 3);
                    } else {
                        ++findFrom;
                    }
                }
            } while (endPos != -1);
        }

        if (startedNow) {
            state = nextState;
            startPos = fromPos;
            return new Pair<>(startPos, null);
        }
        if (state == 1) {
            do {
                endPos = text.indexOf("\'\'\'", fromPos);
                if (endPos != -1) {
                    if (endPos == 0 || text.charAt(endPos - 1) != '\\') {
                        state = 0;
                        return new Pair<>(startPos, endPos + 3);
                    } else {
                        ++fromPos;
                    }
                }
            } while (endPos != -1);
            return new Pair<>(null, null);
        } else if (state == 2) {
            do {
                endPos = text.indexOf("\"\"\"", fromPos);
                if (endPos != -1) {
                    if (endPos == 0 || text.charAt(endPos - 1) != '\\') {
                        state = 0;
                        return new Pair<>(startPos, endPos + 3);
                    } else {
                        ++fromPos;
                    }
                }
            } while (endPos != -1);
            return new Pair<>(null, null);
        } else {
            return new Pair<>(null, null);
        }
    }

    public boolean started() {
        return state > 0;
    }
}
