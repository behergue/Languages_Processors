package ast;

import java.util.List;

public class AS {
	
  public Case creaCase(E valor, List<Instruccion> cuerpo, int fila, int col) {
	  return new Case(valor, cuerpo, fila, col);
  }
  
  public E and(E opnd1, E opnd2, int fila, int col) {
	  return new And(opnd1,opnd2, fila, col);
  }
  
  public E asterisco(E var, int fila, int col) {
	  return new Asterisco(var, fila, col);
  }
  
  public E car(String var, int fila, int col) {
	  return new Car(var, fila, col);
  }
  
  public E corchetes(E opnd1, E opnd2, int fila, int col) {
	  return new Corchetes(opnd1,opnd2, fila, col);
  }
  
  public E dist(E opnd1, E opnd2, int fila, int col) {
	  return new Dist(opnd1,opnd2, fila, col);
  }
  
  public E divD(E opnd1, E opnd2, int fila, int col) {
	  return new DivD(opnd1,opnd2, fila, col);
  }
  
  public E divE(E opnd1, E opnd2, int fila, int col) {
	  return new DivE(opnd1,opnd2, fila, col);
  }
  
  public E ent(String var, int fila, int col) {
	  return new Ent(var, fila, col);
  }
  
  public E falso(int fila, int col) {
	  return new Falso(fila, col);
  }
  
  public E iden(String var, int fila, int col) {
	  return new Iden(var, fila, col);
  }
  
  public E igig(E opnd1, E opnd2, int fila, int col) {
	  return new Igig(opnd1,opnd2, fila, col);
  }
  
  public E llamadaFunc(E iden, List<E> var, int fila, int col) {
	  return new LlamadaFunc(iden, var, fila, col);
  }
  
  public E mai(E opnd1, E opnd2, int fila, int col) {
	  return new Mai(opnd1,opnd2, fila, col);
  }
  
  public E mas(E opnd1, int fila, int col) {
	  return new Mas(opnd1, fila, col);
  }
  
  public E mayor(E opnd1, E opnd2, int fila, int col) {
	  return new Mayor(opnd1,opnd2, fila, col);
  }
 
  public E mei(E opnd1, E opnd2, int fila, int col) {
	  return new Mei(opnd1,opnd2, fila, col);
  }
  
  public E menor(E opnd1, E opnd2, int fila, int col) {
	  return new Menor(opnd1,opnd2, fila, col);
  }
  
  public E menos(E opnd1, int fila, int col) {
	  return new Menos(opnd1, fila, col);
  }
  
  public E mod(E opnd1, E opnd2, int fila, int col) {
	  return new Mod(opnd1,opnd2, fila, col);
  }
  
  public E mul(E opnd1, E opnd2, int fila, int col) {
	  return new Mul(opnd1,opnd2, fila, col);
  }
  
  public E neg(E opnd1, int fila, int col) {
	  return new Neg(opnd1, fila, col);
  }
  
  public E or(E opnd1, E opnd2, int fila, int col) {
	  return new Or(opnd1,opnd2, fila, col);
  }
  
  public E punto(E opnd1, E opnd2, int fila, int col) {
	  return new Punto(opnd1,opnd2, fila, col);
  }
  
  public E real(String var, int fila, int col) {
	  return new Real(var, fila, col);
  }
  
  public E resta(E opnd1, E opnd2, int fila, int col) {
	  return new Resta(opnd1,opnd2, fila, col);
  }
  
  public E suma(E opnd1, E opnd2, int fila, int col) {
	  return new Suma(opnd1,opnd2, fila, col);
  }
  
  public E verdad(int fila, int col) {
	  return new Verdad(fila, col);
  }
  
  public Instruccion insAsig(E iden, List<E> lista, E valor, int fila, int col) {
	  return new InsAsig(iden, lista, valor, fila, col);
  }
  
  public Instruccion insDec(Tipo tipo, E iden, E valor, int fila, int col) {
	  return new InsDec(tipo, iden, valor, fila, col);
  }
  
  public Instruccion insDec(Tipo tipo, E iden, int fila, int col) {
	  return new InsDec(tipo, iden, fila, col);
  }
  
  public Instruccion insDecFunc(Tipo tipo, E nombre, List<Instruccion> atributos, List<Instruccion> cuerpo, E devuelve, int fila, int col) {
	  return new InsDecFunc(tipo, nombre, atributos, cuerpo, devuelve, fila, col);
  }
  
  public Instruccion insDecProc(E nombre, List<Instruccion> atributos, List<Instruccion> cuerpo, int fila, int col) {
	  return new InsDecProc(nombre, atributos, cuerpo, fila, col);
  }
  
  public Instruccion insFor(Instruccion asig, E cond, Instruccion paso, List<Instruccion> cuerpo, int fila, int col) {
	  return new InsFor(asig, cond, paso, cuerpo, fila, col);
  }
  
  public Instruccion insIf(E cond, List<Instruccion> listaif, List<Instruccion> listaelse, int fila, int col) {
	  return new InsIf(cond, listaif, listaelse, fila, col);
  }
  
  public Instruccion insIf(E cond, List<Instruccion> listaif, int fila, int col) {
	  return new InsIf(cond, listaif, fila, col);
  }
  
  public Instruccion insLlamadaProc(E iden, List<E> lista, int fila, int col) {
	  return new InsLlamadaProc(iden, lista, fila, col);
  }
  
  public Instruccion insMasmas(E opnd1, int fila, int col) {
	  return new InsMasMas(opnd1, fila, col);
  }
  
  public Instruccion insMenosmenos(E opnd1, int fila, int col) {
	  return new InsMenosMenos(opnd1, fila, col);
  }
  
  public Instruccion insStruct(E nombretipo, List<Instruccion> cuerpo, int fila, int col) {
	  return new InsStruct(nombretipo, cuerpo, fila, col);
  }
  
  public Instruccion insSwitch(E variable, List<Case> cases, int fila, int col) {
	  return new InsSwitch(variable, cases, fila, col);
  }
  
  public Instruccion insWhile(E cond, List<Instruccion> cuerpo, int fila, int col) {
	  return new InsWhile(cond, cuerpo, fila, col);
  }
  
  public New nuevo(int fila, int col) {
	  return new New(fila, col);
  }
  
  public Tipo tipo(String nombre, List<E> lista, E valorini, int fila, int col) {
	  return new Tipo(nombre, lista, valorini, fila, col);
  }

  public static void pintaEspacios(int ini) {
	  String s = "";
		
		for(int i = 0; i < ini; i++) {
			s += " ";
		}
		
		System.out.print(s);
  }
}
