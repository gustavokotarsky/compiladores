# Compilador
Trabalho de compiladores PUC-MG desenvolvido em Java

Grupo:

[Fabio Melo](https://github.com/fabioscmelo)

[Gustavo Lescowicz Kotarsky](https://github.com/gustavokotarsky)


|   Parte|   Valor|  Nota|
|-|-|-|
|   Analise Léxica/Sintática| 10| 10|
|   Analise Semântica|  20| 20|


Automato:

<img src="https://github.com/gustavokotarsky/compiladores/blob/master/Automato.jpg?raw=true">

Gramática:

##S -> {D}+ main {C}+end
##D -> (integer | boolean | byte | string) id [= [-]v_const] {,id [= [-]v_const]}; | const id = [-]v_const;
##C -> id = EXP;|
while ‘(‘ EXP ’)’ W |
if ‘(‘EXP’)’ then ( C [else (W) ] | begin {C} end [else (W) ] ) |
; |
readln’(‘ id ’)’; |
(write | writeln)’(‘ EXP{,EXP} ’)’;
##W -> begin {C} end | C


<h3>EXP</h3>-> EXPS [(==|!=|<|>|<=|>=) EXPS] 
<h3>EXPS</h3>-> [+|-] T {(+|-|or) T}
<h3>T</h3>-> F {(*|and|/) F}
<h3>F</h3>-> ‘(‘ EXP ’)’| id | v_const | not F

