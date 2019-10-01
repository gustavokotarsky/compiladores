/*
Fabio Silva Campos Melo
Gustavo Lescowicz Kotarsky
Lucas Dutra Ponce de Leon
*/

import java.io.BufferedReader;
import java.io.FileReader;

public class Parser {

    private BufferedReader bufferedReader, bufferedReader2;
    private AnalisadorLexico lexico;
    private TabelaSimbolo tabelaSimbolo;
    private Simbolo simbolo;


    public Parser(String fileName) {

        try {
            /*
            bufferedReader = new BufferedReader(new FileReader(fileName));
            bufferedReader2 = new BufferedReader(new FileReader(fileName));

            lexico = new AnalisadorLexico(bufferedReader);
            tabelaSimbolo = new TabelaSimbolo();

            simbolo = lexico.automatoFinito();
            if (simbolo.getToken() != -1)
                System.out.println(simbolo.getToken() + " " + simbolo.getLexema());
            while (simbolo.getToken() != -1) {
                simbolo = lexico.automatoFinito();
                if (simbolo.getToken() != -1)
                    System.out.println(simbolo.getToken() + " " + simbolo.getLexema() + " "+ simbolo.getTipo());
            }
             */

            bufferedReader = new BufferedReader(new FileReader(fileName));
            lexico = new AnalisadorLexico(bufferedReader);
            tabelaSimbolo = new TabelaSimbolo();
            simbolo = lexico.automatoFinito();


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    void casaToken(byte tokenEsperado) {

        if (simbolo.getToken() == tokenEsperado) {
           //System.out.println(simbolo.getToken() + " " + simbolo.getLexema() + " " + simbolo.getTipo());
            simbolo = lexico.automatoFinito();

        } else {

            if (lexico.EOF) {
                System.out.println(lexico.linha + " :fim de arquivo não esperado");
                System.exit(0);
            } else {
                System.out.println(lexico.linha + " :token nao esperado" + "[" + lexico.lexema + "]");
                System.exit(0);
            }
        }
    }


    void S() {

        while (simbolo.getToken() == tabelaSimbolo.INTEGER ||
                simbolo.getToken() == tabelaSimbolo.BOOLEAN ||
                simbolo.getToken() == tabelaSimbolo.BYTE ||
                simbolo.getToken() == tabelaSimbolo.STRING ||
                simbolo.getToken() == tabelaSimbolo.CONST) {
            D();
        }
        casaToken(tabelaSimbolo.MAIN);
        while (simbolo.getToken() == tabelaSimbolo.ID ||
                simbolo.getToken() == tabelaSimbolo.WHILE ||
                simbolo.getToken() == tabelaSimbolo.IF ||
                simbolo.getToken() == tabelaSimbolo.PONTO_VIRGULA ||
                simbolo.getToken() == tabelaSimbolo.READ_LINE ||
                simbolo.getToken() == tabelaSimbolo.WRITE ||
                simbolo.getToken() == tabelaSimbolo.WRITE_LINE) {
            C();
        }
        
        casaToken(tabelaSimbolo.END);
   
     
    }

    void D() {

        if (simbolo.getToken() == tabelaSimbolo.INTEGER) {
            casaToken(tabelaSimbolo.INTEGER);
        } else if (simbolo.getToken() == tabelaSimbolo.BOOLEAN) {
            casaToken(tabelaSimbolo.BOOLEAN);
        } else if (simbolo.getToken() == tabelaSimbolo.BYTE) {
            casaToken(tabelaSimbolo.BYTE);
        } else if (simbolo.getToken() == tabelaSimbolo.STRING) {
            casaToken(tabelaSimbolo.STRING);
        } else {
            casaToken(tabelaSimbolo.CONST);
            casaToken(tabelaSimbolo.ID);
            casaToken(tabelaSimbolo.IGUAL);
            if (simbolo.getToken() == tabelaSimbolo.SUBTRACAO) {
                casaToken(tabelaSimbolo.SUBTRACAO);
            }
            casaToken(tabelaSimbolo.VALORCONST);
            casaToken(tabelaSimbolo.PONTO_VIRGULA);
        }


        if (simbolo.getToken() == tabelaSimbolo.ID) {
            casaToken(tabelaSimbolo.ID);

            if (simbolo.getToken() == tabelaSimbolo.IGUAL) {
                casaToken(tabelaSimbolo.IGUAL);
                if (simbolo.getToken() == tabelaSimbolo.SUBTRACAO) {
                    casaToken(tabelaSimbolo.SUBTRACAO);
                }
                casaToken(tabelaSimbolo.VALORCONST);
            }
            while (simbolo.getToken() == tabelaSimbolo.VIRGULA) {

                casaToken(tabelaSimbolo.VIRGULA);
                casaToken(tabelaSimbolo.ID);

                if (simbolo.getToken() == tabelaSimbolo.IGUAL) {
                    casaToken(tabelaSimbolo.IGUAL);
                    if (simbolo.getToken() == tabelaSimbolo.SUBTRACAO) {
                        casaToken(tabelaSimbolo.SUBTRACAO);
                    }
                    casaToken(tabelaSimbolo.VALORCONST);

                }
            }
            casaToken(tabelaSimbolo.PONTO_VIRGULA);
        }
    }

    void C() {
        if (simbolo.getToken() == tabelaSimbolo.ID) {
            casaToken(tabelaSimbolo.ID);
            casaToken(tabelaSimbolo.IGUAL);
            EXP();
            casaToken(tabelaSimbolo.PONTO_VIRGULA);
        }else if(simbolo.getToken() == tabelaSimbolo.WHILE){
            casaToken(tabelaSimbolo.WHILE);
            casaToken(tabelaSimbolo.A_PARENTESES);
            EXP();
            casaToken(tabelaSimbolo.F_PARENTESES);
            W();
        }else if(simbolo.getToken() == tabelaSimbolo.IF){
            casaToken(tabelaSimbolo.IF);
            casaToken(tabelaSimbolo.A_PARENTESES);
            EXP();
            casaToken(tabelaSimbolo.F_PARENTESES);
            casaToken(tabelaSimbolo.THEN);
            //VERIFICA SE CHAMA C();
            if (simbolo.getToken() == tabelaSimbolo.ID || simbolo.getToken() == tabelaSimbolo.WHILE || simbolo.getToken() == tabelaSimbolo.IF || simbolo.getToken() == tabelaSimbolo.PONTO_VIRGULA ||
                    simbolo.getToken() == tabelaSimbolo.READ_LINE || simbolo.getToken() == tabelaSimbolo.WRITE || simbolo.getToken() == tabelaSimbolo.WRITE_LINE){
                C();
                if(simbolo.getToken() == tabelaSimbolo.ELSE){
                    casaToken(tabelaSimbolo.ELSE);
                    W();
                }
            }else if (simbolo.getToken() == tabelaSimbolo.BEGIN) {
                casaToken(tabelaSimbolo.BEGIN);
                while (simbolo.getToken() == tabelaSimbolo.ID || simbolo.getToken() == tabelaSimbolo.WHILE
                        || simbolo.getToken() == tabelaSimbolo.IF || simbolo.getToken() == tabelaSimbolo.PONTO_VIRGULA
                        || simbolo.getToken() == tabelaSimbolo.READ_LINE || simbolo.getToken() == tabelaSimbolo.WRITE
                        || simbolo.getToken() == tabelaSimbolo.WRITE_LINE) {
                    C();
                }
                casaToken(tabelaSimbolo.END);
                if (simbolo.getToken() == tabelaSimbolo.ELSE) {
                    casaToken(tabelaSimbolo.ELSE);
                    W();
                }
            }
        } else if (simbolo.getToken() == tabelaSimbolo.PONTO_VIRGULA) {
            casaToken(tabelaSimbolo.PONTO_VIRGULA);
        } else if (simbolo.getToken() == tabelaSimbolo.READ_LINE) {
            casaToken(tabelaSimbolo.READ_LINE);
            casaToken(tabelaSimbolo.A_PARENTESES);
            casaToken(tabelaSimbolo.ID);
            casaToken(tabelaSimbolo.F_PARENTESES);
            casaToken(tabelaSimbolo.PONTO_VIRGULA);
        } else if (simbolo.getToken() == tabelaSimbolo.WRITE || simbolo.getToken() == tabelaSimbolo.WRITE_LINE) {
            if (simbolo.getToken() == tabelaSimbolo.WRITE) {
                casaToken(tabelaSimbolo.WRITE);
            } else {
                casaToken(tabelaSimbolo.WRITE_LINE);
            }
            casaToken(tabelaSimbolo.A_PARENTESES);
            EXP();
            while(simbolo.getToken() == tabelaSimbolo.VIRGULA){
                casaToken(tabelaSimbolo.VIRGULA);
                EXP();
            }
            casaToken(tabelaSimbolo.F_PARENTESES);
            casaToken(tabelaSimbolo.PONTO_VIRGULA);
        }

    }
    void W(){
        if (simbolo.getToken() == tabelaSimbolo.BEGIN){
            casaToken(tabelaSimbolo.BEGIN);
            while (simbolo.getToken() == tabelaSimbolo.ID ||
                    simbolo.getToken() == tabelaSimbolo.WHILE ||
                    simbolo.getToken() == tabelaSimbolo.IF ||
                    simbolo.getToken() == tabelaSimbolo.PONTO_VIRGULA ||
                    simbolo.getToken() == tabelaSimbolo.READ_LINE ||
                    simbolo.getToken() == tabelaSimbolo.WRITE ||
                    simbolo.getToken() == tabelaSimbolo.WRITE_LINE) {
                C();
            }
            casaToken(tabelaSimbolo.END);
        }else{
            C();
        }
    }

    void EXP(){
        // == != < > <= >=
        EXPS();
        if(simbolo.getToken() == tabelaSimbolo.IGUAL_IGUAL || simbolo.getToken() == tabelaSimbolo.DIFERENTE || simbolo.getToken() == tabelaSimbolo.MENOR ||
                simbolo.getToken() == tabelaSimbolo.MAIOR || simbolo.getToken() == tabelaSimbolo.MENORIGUAL || simbolo.getToken() == tabelaSimbolo.MAIORIGUAL) {
            if (simbolo.getToken() == tabelaSimbolo.IGUAL_IGUAL) {
                casaToken(tabelaSimbolo.IGUAL_IGUAL);
            } else if (simbolo.getToken() == tabelaSimbolo.DIFERENTE) {
                casaToken(tabelaSimbolo.DIFERENTE);
            } else if (simbolo.getToken() == tabelaSimbolo.MENOR) {
                casaToken(tabelaSimbolo.MENOR);
            } else if (simbolo.getToken() == tabelaSimbolo.MAIOR) {
                casaToken(tabelaSimbolo.MAIOR);
            } else if (simbolo.getToken() == tabelaSimbolo.MENORIGUAL) {
                casaToken(tabelaSimbolo.MENORIGUAL);
            } else{
                casaToken(tabelaSimbolo.MAIORIGUAL);
            }
            EXPS();
        }
    }
    void EXPS(){
        // + - or
        if (simbolo.getToken() == tabelaSimbolo.SOMA) {
            casaToken(tabelaSimbolo.SOMA);
        }else if (simbolo.getToken() == tabelaSimbolo.SUBTRACAO) {
            casaToken(tabelaSimbolo.SUBTRACAO);
        }
        T();
        while (simbolo.getToken() == tabelaSimbolo.SOMA || simbolo.getToken() == tabelaSimbolo.SUBTRACAO || simbolo.getToken() == tabelaSimbolo.OR) {
            if (simbolo.getToken() == tabelaSimbolo.SOMA) {
                casaToken(tabelaSimbolo.SOMA);
            }else if (simbolo.getToken() == tabelaSimbolo.SUBTRACAO) {
                casaToken(tabelaSimbolo.SUBTRACAO);
            }else if (simbolo.getToken() == tabelaSimbolo.OR) {
                casaToken(tabelaSimbolo.OR);
            }
            T();
        }
    }
    void T(){
        // * / and
        F();
        while(simbolo.getToken() == tabelaSimbolo.MULTIPLICACAO || simbolo.getToken() == tabelaSimbolo.DIVISAO || simbolo.getToken() == tabelaSimbolo.AND) {
            if (simbolo.getToken() == tabelaSimbolo.MULTIPLICACAO) {
                casaToken(tabelaSimbolo.MULTIPLICACAO);
            } else if (simbolo.getToken() == tabelaSimbolo.DIVISAO) {
                casaToken(tabelaSimbolo.DIVISAO);
            } else if (simbolo.getToken() == tabelaSimbolo.AND) {
                casaToken(tabelaSimbolo.AND);
            }
            F();
        }
    }
    void F(){
        // exp id const notF
        if (simbolo.getToken() == tabelaSimbolo.A_PARENTESES) {
            casaToken(tabelaSimbolo.A_PARENTESES);
            EXP();
            casaToken(tabelaSimbolo.F_PARENTESES);
        }else if(simbolo.getToken() == tabelaSimbolo.ID){
            casaToken(tabelaSimbolo.ID);
        }else if(simbolo.getToken() == tabelaSimbolo.VALORCONST){
            casaToken(tabelaSimbolo.VALORCONST);
        }else if(simbolo.getToken() == tabelaSimbolo.NOT){
            casaToken(tabelaSimbolo.NOT);
            F();
        }
    }

}
