parser grammar HooverParser;

options {
    language   = Java;
    output     = AST;
    superClass = AbstractHooverParser;
    tokenVocab = HooverLexer;
    backtrack  = true;
}

@header {
    package edu.programming.hoover.lang;
}

parseApplication : header? block EOF ;

header           : PROGRAM^ programName ;

programName      : LITERAL ;

block            : ( operation | ifStatement | loopStatement )* ;

operation        : ( MOVE^ direction+ ) | TAKE | PUT | STOP ;

direction        : DIR_LEFT | DIR_RIGHT | DIR_UP | DIR_DOWN ;

ifStatement      : ifBlock thenBlock ( elseBlock )? END! IF! ;

ifBlock          : IF^ condition ;

elseifBlock      : ELSEIF^ condition ;

thenBlock        : THEN^ block ;

elseBlock        : ( ELSE^ block ) | ( elseIfBlock thenBlock )+ ;

elseIfBlock      : ELSEIF^ condition ;

loopStatement    : whileBlock loopBlock END! LOOP! ;

whileBlock       : WHILE^ condition ;

loopBlock        : LOOP^ block ;

condition        : orCondition ;

orCondition      : andCondition ( OR^ andCondition )* ;

andCondition     : notCondition ( AND^ notCondition )* ;

notCondition     : ( NOT^ )? complexCondition ;

complexCondition : atomicCondition | ( LEFT_PAR! condition RIGHT_PAR! ) ;

atomicCondition  : MATCH | ( CAN^ direction+ ) | ( ( CELL | BAG )^ ( FULL | EMPTY ) ) | comparison ;

comparison       : arithmetic ( CMP_GE | CMP_LE | CMP_NE | CMP_EQ | CMP_GT | CMP_LT )^ arithmetic ;

arithmetic       : modResult ;

modResult        : plusMinusResult ( MOD^ plusMinusResult )? ;

plusMinusResult  : mulDivResult ( ( PLUS | MINUS )^ mulDivResult )* ;

mulDivResult     : number ( ( MUL | DIV )^ number )* ;

number           : INTEGER | ( LEFT_PAR! arithmetic RIGHT_PAR! ) ;