/*
Fabio Silva Campos Melo
Gustavo Lescowicz Kotarsky
Lucas Dutra Ponce de Leon
*/

public class Simbolo {

    private String lexema = "";
    private byte token;
    private String classe = "";
    private String tipo = "";
    private int tamanho = 0;
    private int endereco;

    public Simbolo(){
        this.token = -1;

    }

    public Simbolo(String lexema, byte token) {
        this.lexema = lexema;
        this.token = token;
        this.endereco = endereco;
    }

    public Simbolo(String lexema,byte token, String tipo){
        this.token = token;
        this.lexema = lexema;
        this.tipo = tipo;
        this.endereco = endereco;
    }


    public byte getToken(){
        return token;
    }

    public int getEnd(){
        return endereco;
    }

    public String getLexema(){
        return lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setToken(byte token) {
        this.token = token;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
