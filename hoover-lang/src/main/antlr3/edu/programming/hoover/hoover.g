grammar Hoover;

options {
	language=Hoover;
	output=AST;
	backtrack=true;
}

tokens {
}

@header {
}

@members {
}

@parser::header {
package edu.programming.hoover.parser;
}

@lexer::header {
package edu.programming.hoover.lexer;
}

@lexer::members {
}

fragment A_ :	'a' | 'A';
fragment B_ :	'b' | 'B';
fragment C_ :	'c' | 'C';
fragment D_ :	'd' | 'D';
fragment E_ :	'e' | 'E';
fragment F_ :	'f' | 'F';
fragment G_ :	'g' | 'G';
fragment H_ :	'h' | 'H';
fragment I_ :	'i' | 'I';
fragment J_ :	'j' | 'J';
fragment K_ :	'k' | 'K';
fragment L_ :	'l' | 'L';
fragment M_ :	'm' | 'M';
fragment N_ :	'n' | 'N';
fragment O_ :	'o' | 'O';
fragment P_ :	'p' | 'P';
fragment Q_ :	'q' | 'Q';
fragment R_ :	'r' | 'R';
fragment S_ :	's' | 'S';
fragment T_ :	't' | 'T';
fragment U_ :	'u' | 'U';
fragment V_ :	'v' | 'V';
fragment W_ :	'w' | 'W';
fragment X_ :	'x' | 'X';
fragment Y_ :	'y' | 'Y';
fragment Z_ :	'z' | 'Z';

fragment DIGIT  : '0'..'9' ;

NUMBER  : (DIGIT)+ ;

