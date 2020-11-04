package ast;

import java.util.List;

public class InsDecProc extends Instruccion{
	
	private E nombre;
	private List<Instruccion> atributos;
	private List<Instruccion> cuerpo;
	private int profundidad;
	private int dir;
	private int tam;
	
	public InsDecProc(E nombre, List<Instruccion> atributos, List<Instruccion> cuerpo, int fila, int col) {
		super();
		this.nombre = nombre;
		this.atributos = atributos;
		this.cuerpo = cuerpo;
		this.fila = fila;
	    this.col = col;
	}
	
	public int getTam() {
		return tam;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public E getNombre() {
		return nombre;
	}

	public List<Instruccion> getAtributos() {
		return atributos;
	}

	public List<Instruccion> getCuerpo() {
		return cuerpo;
	}
		
	public void incrementa(int i) {
		this.tam += i;
	}

	public TipoI tipo() {return TipoI.PROC;}
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("procedimiento");
		nombre.muestra(ini + 3);
		AS.pintaEspacios(ini + 3);
		
		if(!atributos.isEmpty()) {
			System.out.println("atributos");
			
			for(Instruccion i : atributos) {
				i.muestra(ini + 6);
			}
		}
		else {
			System.out.println("sin atributos");
		}
		
		AS.pintaEspacios(ini + 3);
		System.out.println("cuerpo");
		
		for(Instruccion i : cuerpo) {
			i.muestra(ini + 6);
		}
	}
}
