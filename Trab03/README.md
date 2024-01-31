# Parser para Lugosi em Javacc

## Implementação de um parser para a Lugosi em Javacc

![image](https://github.com/guilhermedallm4/Compilers/assets/54902373/cc99fab5-d175-4d6b-8512-278d4ebd2247)

## Gramática Apresentada
```
LUGOSI -> MAIN FUNC?

MAIN -> "void" "main" "{" VARDECL SEQCOMANDOS "}"

VARDECL -> VARDECL "let" TIPO TOKEN_id ";" | vazio

TIPO -> "float" | "bool" | "void"

SEQCOMANDOS -> SEQCOMANDOS COMANDO | vazio

COMANDO -> TOKEN_id ":=" EXP ";"
| TOKEN_id "(" LISTAEXP? ")" ";"
| "if" EXP "{" SEQCOMANDOS "}" ";"
| "while" EXP "do" "{" SEQCOMANDOS "}" ";"
| TOKEN_id ":=" "readIO" "(" ")"
| "return" EXP ";"
| "printIO" EXP ";"

EXP -> "(" EXP OP EXP ")" | FATOR

FATOR -> TOKEN_id | TOKEN_id "(" LISTAEXP? ")"
| TOKEN_numliteral | "true" | "false"

OP -> "+" | "-" | "*" | "/" | "&&" | "||" | "<" | ">" | "=="

LISTAEXP -> EXP | LISTAEXP "," EXP

FUNC -> FUNC "def" TIPO TOKEN_id "(" LISTAARG? ")" "{" VARDECL SEQCOMANDOS "}"
| "def" TIPO TOKEN_id "(" LISTAARG? ")" "{" VARDECL SEQCOMANDOS "}"

LISTAARG -> TIPO TOKEN_id | LISTAARG "," TIPO TOKEN_id
```
## Gramática Fatorada e sem Recursão a Esquerda

```
LUGOSI -> MAIN FUNC?

MAIN -> "void" "main" "{" VARDECL SEQCOMANDOS "}"

VARDECL -> "let" TIPO TOKEN_id ";" VARDECLLINE 
VARDECLLINE -> "let" TIPO TOKEN_id ";" VARDECLLINE | vazio

TIPO -> "float" | "bool" | "void"

SEQCOMANDOS -> COMANDO SEQCOMANDOSLINE
SEQCOMANDOSLINE -> COMANDO SEQCOMANDOSLINE | Vazio

COMANDO -> TOKEN_id TOKENIDLINE
| "if" EXP "{" SEQCOMANDOS "}" ";"
| "while" EXP "do" "{" SEQCOMANDOS "}" ";""
| "return" EXP ";"
| "printIO" EXP ";"

TOKENIDLINE -> ":=" TOKENIDLINELINE | "(" LISTAEXP? ")" ";" 
TOKENIDLINELINE -> EXP ";" | "readIO" "(" ")"

EXP -> "(" EXP OP EXP ")" | FATOR

FATOR -> TOKEN_id FATORLINE| TOKEN_numliteral | "true" | "false"
FATORLINE -> ("(" LISTAEXP? ")")?

OP -> "+" | "-" | "*" | "/" | "&&" | "||" | "<" | ">" | "=="

LISTAEXP -> EXP LISTAEXPLINE
LISTAEXPLINE -> ("," EXP LISTAEXPLINE)?

FUNC -> "def" TIPO TOKEN_id "(" LISTAARG? ")" "{" VARDECL SEQCOMANDOS "}" FUNCLINE
FUNCLINE -> ("def" TIPO TOKEN_id "(" LISTAARG? ")" "{" VARDECL SEQCOMANDOS "}" FUNCLINE)?

LISTAARG -> TIPO TOKEN_id LISTAARGLINE
LISTAARGLINE -> ("," TIPO TOKEN_id LISTAARGLINE)?
```
## Comandos para execução
```
Compilação:
javacc Lugosi.jj 

javac *.java

Execução:
java Lugosi prog1.lug

```
