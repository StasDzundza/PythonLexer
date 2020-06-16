package com.university.lexer.token;

public enum TokenName {
    WHITESPACE, // \t, space, ......
    INDENT,
    DEDENT,
    NEW_LINE,
    COMMENT,//#
    OPERATOR, //+,-,*,/, //, **,&,|,^,=,+=,-=,%=,*=,/=,&=,|=,^=, //=, ~, <<, >>, %
    COMPARISON_OPERATOR, //<,<=,>,>=,==,!=
    DATA_TYPE, //int,float,complex,str,bool
    KEYWORD, //import,as,class,def,pass,if,elif,else,try,except,throw,for,while,in,is,await,None,raise,True,False,return
    // and,or,lambda,break,continue,from,assert,with,not,async,yield,global,del
    SEPARATOR, //, ; :
    BRACKET, // (, ), [, ], {, }
    DOT, // .
    IDENTIFIER,
    NUMBER,
    STRING,
    MULTILINE_STRING,
    ERROR_TOKEN,
    NOT_ENDED_MULTILINE_STRING_ERROR
}
