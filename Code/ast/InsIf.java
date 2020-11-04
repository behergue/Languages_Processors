package ast;

import java.util.List;

public class InsIf extends Instruccion{
	private E condicion;
	private List<Instruccion> listaif;
	private List<Instruccion> listaelse;

	public InsIf(E condicion, List<Instruccion> listaif, List<Instruccion> listaelse, int fila, int col) {
		super();
		this.condicion = condicion;
		this.listaif = listaif;
		this.listaelse = listaelse;
	    this.fila = fila;
	    this.col = col;
	}
	
	public InsIf(E condicion, List<Instruccion> listaif, int fila, int col) {
		super();
		this.condicion = condicion;
		this.listaif = listaif;
	    this.fila = fila;
	    this.col = col;
	}
	
	public E getCondicion() {
		return condicion;
	}

	public List<Instruccion> getListaif() {
		return listaif;
	}

	public List<Instruccion> getListaelse() {
		return listaelse;
	}

	public TipoI tipo() {return TipoI.IF;}
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("if");
		
		AS.pintaEspacios(ini + 3);
		System.out.println("condición");
		condicion.muestra(ini + 6);
		
		AS.pintaEspacios(ini + 3);
		System.out.println("cuerpo del if");
		for(Instruccion i : listaif) {
			i.muestra(ini + 6);
		}
		
		if(listaelse != null) {
			AS.pintaEspacios(ini);
			System.out.println("else");
			AS.pintaEspacios(ini + 3);
			System.out.println("cuerpo del else");
			for(Instruccion i : listaelse) {
				i.muestra(ini + 6);
			}
		}
	}
}
