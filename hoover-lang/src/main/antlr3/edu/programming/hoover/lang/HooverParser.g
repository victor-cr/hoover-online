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

parseApplication : header EOF
                 ;

header           : program programName eoc
                 ;

program          : PROGRAM ;

programName      : LITERAL ;

eoc              : TERMINATOR ;
