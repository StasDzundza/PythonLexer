package com.university.lexer.token;

public class Location {
    private int line,column;

    public Location(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString(){
        return "[line:" + line + ",column:" + column + "]";
    }
}
