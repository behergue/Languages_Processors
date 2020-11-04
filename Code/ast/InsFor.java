package ast;

import java.util.List;

public class InsFor extends Instruccion{
	private Instruccion asig;
	private E cond;
	private NodoArbol variableBucle;
	private Instruccion paso;
	private List<Instruccion> cuerpo;

	public InsFor(Instruccion asig1, E cond, Instruccion paso, List<Instruccion> cuerpo, int fila, int col) {
		super();
		this.asig = asig1;
		this.cond = cond;
		this.paso = paso;
		this.cuerpo = cuerpo;
		this.fila = fila;
	    this.col = col;
	}
	
	public NodoArbol getVariableBucle() {
		return variableBucle;
	}

	public void setVariableBucle(NodoArbol variableBucle) {
		this.variableBucle = variableBucle;
	}

	public Instruccion getAsig() {
		return asig;
	}

	public E getCond() {
		return cond;
	}

	public Instruccion getPaso() {
		return paso;
	}

	public List<Instruccion> getCuerpo() {
		return cuerpo;
	}

	public TipoI tipo() { return TipoI.FOR; }
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("for");
		
		AS.pintaEspacios(ini + 3);
		System.out.println("inicialización");
		asig.muestra(ini + 6);
		
		AS.pintaEspacios(ini + 3);
		System.out.println("condición de parada");
		cond.muestra(ini + 6);
		
		AS.pintaEspacios(ini + 3);
		System.out.println("paso");
		paso.muestra(ini + 6);
		
		AS.pintaEspacios(ini + 3);
		System.out.println("cuerpo del for");
		
		for(Instruccion i : cuerpo) {
			i.muestra(ini + 3);
		}
	}
}
