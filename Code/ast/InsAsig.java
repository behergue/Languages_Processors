 package ast;

import java.util.List;

public class InsAsig extends Instruccion{
	private E iden;
	private List<E> lista;
	private E valor;

	public InsAsig(E iden, List<E> lista, E valor, int fila, int col) {
		super();
		this.iden = iden;
		this.lista = lista;
		this.valor = valor;
	    this.fila = fila;
	    this.col = col;
	}
	
	public E getIden() {
		return iden;
	}

	public List<E> getLista() {
		return lista;
	}

	public E getValor() {
		return valor;
	}
	
	public TipoI tipo() {return TipoI.ASIG;}
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("asignación");
		iden.muestra(ini + 3);
		
		if(lista != null) {
			for(E i : lista) {
				i.muestra(ini + 6);
			}
		}
	
		valor.muestra(ini + 3);
	}
}
