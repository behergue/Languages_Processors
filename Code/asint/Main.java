package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import alex.AnalizadorLexicoTiny;
import asem.AnalizadorSemantico;
import ast.Instruccion;
import codegeneration.GeneraCodigo;
import errors.GestionErroresTiny;

public class Main {
   public static void main(String[] args) throws Exception {
	   
     Reader input = new InputStreamReader(new FileInputStream("input.txt"));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
	 
	 asint.setScanner(alex);

	 List<Instruccion> aux = null;
	 try {
		 aux = (List<Instruccion>) asint.parse().value;
	 } catch(Exception e) {
		 System.err.println("No ha sido posible la recuperaci�n despu�s del �ltimo error");
	 }
	 
	 int x = GestionErroresTiny.numErroresSintacticos;
	 
	 if(x == 1) {
		 System.err.println("Se ha detectado 1 error sint�ctico");
		 System.exit(1);
	 }
	 
	 else if(x > 1) {
		 System.err.println("Se han detectado " + x + " errores sint�cticos");
		 System.exit(1);
	 }
	 
	 AnalizadorSemantico asem = new AnalizadorSemantico(aux);
	 asem.analizaSemantica();
	 
	 int y = GestionErroresTiny.numErroresSemanticos;
	 
	 if(y == 1) {
		 System.err.println("Se ha detectado 1 error sem�ntico");
		 System.exit(1);
	 }
	 
	 else if(y > 1) {
		 System.err.println("Se han detectado " + y + " errores sem�nticos");
		 System.exit(1);
	 }
	 
	 for(Instruccion i : aux) {
		 i.muestra(0);
	 }
	 
	 GeneraCodigo gc = new GeneraCodigo();
	 gc.generaCodigo(aux);
   }
}