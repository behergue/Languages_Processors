package ast;

import java.util.List;

public class InsWhile extends Instruccion{
	private E condicion;
	private List<Instruccion> cuerpo;

	public InsWhile(E condicion, List<Instruccion> cuerpo, int fila, int col) {
		super();
		this.condicion = condicion;
		this.cuerpo = cuerpo;
	    this.fila = fila;
	    this.col = col;
	}
	
	public E getCondicion() {
		return condicion;
	}

	public List<Instruccion> getCuerpo() {
		return cuerpo;
	}

	public TipoI tipo() {return TipoI.WHILE;}
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("while");
		
		AS.pintaEspacios(ini + 3);
		System.out.println("condición");
		condicion.muestra(ini + 6);
		
		AS.pintaEspacios(ini + 3);
		System.out.println("cuerpo del while");
		
		for(Instruccion i : cuerpo) {
			i.muestra(ini + 6);
		}
	}
}
