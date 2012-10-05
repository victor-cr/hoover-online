parser grammar HooverParser;

options {
    language   = Java;
    output     = AST;
    superClass = AbstractHooverParser;
    tokenVocab = HooverLexer;
}

@header {
    package edu.programming.hoover.lang;
}

parseApplication : header body EOF ;

header           : programCommand programName eol ;

programCommand   : PROGRAM ;

programName      : LITERAL ;

eol              : TERMINATOR ;

body             : ( operation | check | loop )+ ;

operation        : (( MOVE direction ) | TAKE | PUT ) eol;

direction        : DIR_LEFT | DIR_RIGHT | DIR_UP | DIR_DOWN ;

check            : IF condition THEN body END_IF ;

loop             : WHILE condition LOOP body END_LOOP ;

condition        : andCheck ;

andCheck         : orCheck (AND orCheck)* ;

orCheck          : complexCheck (OR complexCheck)* ;

complexCheck     : atomicCheck | ( LEFT_PAR condition RIGHT_PAR ) ;

atomicCheck      : MATCH | ( CAN direction ) ;

/*
negativeCheck    : NOT positiveBoolean ;
*/
