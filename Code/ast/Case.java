package ast;

import java.util.List;

public class Case extends NodoArbol{
	private E valor;
	private NodoArbol ref;
	private List<Instruccion> cuerpo;

	public Case(E valor, List<Instruccion> cuerpo, int fila, int col) {
		super();
		this.valor = valor;
		this.cuerpo = cuerpo;
		this.fila = fila;
	    this.col = col;
	}
	
	public Case(List<Instruccion> cuerpo) {
		super();
		this.cuerpo = cuerpo;
	}
	
	public NodoArbol getRef() {
		return ref;
	}

	public void setRef(NodoArbol ref) {
		this.ref = ref;
	}

	public E getValor() {
		return valor;
	}

	public List<Instruccion> getCuerpo() {
		return cuerpo;
	}
	
	public TipoN tipoNodo() {return TipoN.CASE;}

	public void muestra(int ini) {
		
		AS.pintaEspacios(ini);
		
		if(valor == null) {
			System.out.println("default");
			AS.pintaEspacios(ini + 3);
			System.out.println("cuerpo del default");
		}
		
		else {
			System.out.println("case");
			valor.muestra(ini + 3);
			AS.pintaEspacios(ini + 3);
			System.out.println("cuerpo del case");
		}
		
		for(Instruccion i : cuerpo) {
			i.muestra(ini + 6);
		}
	}
}
