package alex;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
   private int fila;
   private int col;
   
   public UnidadLexica(int fila, int col, int clase, String lexema) {
     super(clase, new TokenValue(lexema, fila, col));
	 this.fila = fila;
	 this.col = col;
   }
   
   public String lexema() {return (String)value;}
   public int fila() {return fila;}
   public int col() {return col;}
}