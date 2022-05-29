grammar Demo;

prog: ( (stat|function)? NEWLINE )*
 ;

block: ( stat? NEWLINE )*
;

stat: IF sign THEN blockif ENDIF 	#if
    | LOOP repetitions block ENDLOOP		#repeat
	| WRITE ID			#write
	| ID '=' expr0		#assign
	| READ ID   		#read
;

expr0:  expr1			#single0
      | expr1 ADD expr1		#add
      | expr1 SUB expr1		#sub
;

expr1:  expr2			#single1
      | expr2 MULT expr2	#mult
      | expr2 DIV expr2	#div
;

expr2:   INT			#int
       | REAL			#real
       | TOINT expr2		#toint
       | TOREAL expr2		#toreal
       | '(' expr0 ')'		#par
       | ID                 #id
;

function: FUNCTION type fparam fblock ENDFUNCTION
;

type: FREAL #freal
     |FINT  #fint
;

fparam: ID

;

fblock: ( stat? NEWLINE )*
;

blockif: block
;


sign: ID '==' INT #equal
     | ID '>' INT #greater
     | ID '<' INT #lower
     | ID '!=' INT #negative
;

repetitions: expr2
;


WRITE:	'write'

;

TOINT: '(int)'
    ;

TOREAL: '(real)'
    ;


READ:	'read'
;

FUNCTION: 'function'
;

ENDFUNCTION:	'endfunction'
;

LOOP:	'loop'
;

ENDLOOP: 'endloop'
;

REAL: '0'..'9'+'.''0'..'9'+
    ;

IF:	'if'
;

THEN:	'then'
;

ENDIF:	'endif'
;

FREAL: 'real'
;
FINT: 'int'
;

ID:   ('a'..'z'|'A'..'Z')+
   ;

INT: '0'..'9'+
    ;

ADD: '+'
    ;

SUB: '-'
    ;

MULT: '*'
    ;

DIV: '/'
    ;

NEWLINE:	'\r'? '\n'
    ;

WS:   (' '|'\t') -> skip;

