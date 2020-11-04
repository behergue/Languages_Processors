package ast;

import java.util.List;

public class InsDecFunc extends Instruccion{
	private List<Instruccion> atributos;
	private List<Instruccion> cuerpo;
	private E devuelve;
	private E nombre;
	private Tipo tipo;
	private int profundidad;
	private int dir;
	private int tam = 0;
	
	public InsDecFunc(Tipo tipo, E nombre, List<Instruccion> atributos, List<Instruccion> cuerpo, 
			E devuelve, int fila, int col) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.atributos = atributos;
		this.cuerpo = cuerpo;
		this.devuelve = devuelve;
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

	public Tipo getTipo() {
		return tipo;
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

	public E getDevuelve() {
		return devuelve;
	}

	public void incrementa(int i) {
		this.tam += i;
	}

	public TipoI tipo() {return TipoI.FUNC;}
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("declaración de función");
		
		AS.pintaEspacios(ini + 3);
		System.out.println("tipo");
		tipo.muestra(ini + 6);
		
		AS.pintaEspacios(ini + 3);
		System.out.println("nombre");
		nombre.muestra(ini + 6);
		
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
		System.out.println("cuerpo de la función");
		
		for(Instruccion i : cuerpo) {
			i.muestra(ini + 6);
		}
		
		AS.pintaEspacios(ini + 3);
		System.out.println("devuelve");
		devuelve.muestra(ini + 6);
	}
}
