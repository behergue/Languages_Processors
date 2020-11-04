package ast;

import java.util.List;

public class Tipo extends NodoArbol{
	private List<E> lista;
	private NodoArbol n;
	private String nombre;
	private String nombreStruct;
	private E valorini;
	private int numDim = 0;
	
	private boolean esPuntero = false;

	public Tipo(String nombre, int numDim, int fila, int col) {
		super();
		this.numDim = numDim;
		this.fila = fila;
	    this.col = col;
		
		if(!nombre.equals("int") && !nombre.equals("bool") &&!nombre.equals("char") &&
				!nombre.equals("float")) {
			this.nombreStruct = nombre;
			this.nombre = "user";
		}
		
		else {
			this.nombre = nombre;
		}
	}

	public Tipo(String nombre, List<E> lista) {
		super();
		this.lista = lista;
		this.numDim = lista.size();
		
		if(!nombre.equals("int") && !nombre.equals("bool") &&!nombre.equals("char") &&
				!nombre.equals("float")) {
			this.nombreStruct = nombre;
			this.nombre = "user";
		}
		else {
			this.nombre = nombre;
		}
	}

	public Tipo(String nombre, List<E> lista, E valorini, int fila, int col) {
		super();
		this.lista = lista;
		this.valorini = valorini;
		this.numDim = lista.size();
		this.fila = fila;
	    this.col = col;
		
		if(!nombre.equals("int") && !nombre.equals("bool") &&!nombre.equals("char") &&
				!nombre.equals("float")) {
			this.nombreStruct = nombre;
			this.nombre = "user";
		}
		
		else {
			this.nombre = nombre;
		}
	}
	
	public Tipo(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public boolean isEsPuntero() {
		return esPuntero;
	}

	public void setEsPuntero(boolean esPuntero) {
		this.esPuntero = esPuntero;
	}

	public String getNombreStruct() {
		return nombreStruct;
	}

	public int getNumDim() {
		return numDim;
	}

	public void setNumDim(int numDim) {
		this.numDim = numDim;
	}

	public NodoArbol getN() {
		return n;
	}

	public void setN(NodoArbol n) {
		this.n = n;
	}

	public String getNombre() {
		return nombre;
	}

	public List<E> getLista() {
		return lista;
	}

	public E getValorini() {
		return valorini;
	}
	
	public TipoN tipoNodo() {return TipoN.TIPO;}
	
	public void muestra(int ini) {
		
		if(!lista.isEmpty()) {
			AS.pintaEspacios(ini);
			System.out.println("array de " + nombre);
			
			if(n != null) {
				AS.pintaEspacios(ini + 3);
				System.out.println("mi referencia es ");
				n.muestra(ini + 6);
			}
			
			AS.pintaEspacios(ini + 3);
			System.out.println("las dimensiones del vector son ");
			for(E i : lista) {
				i.muestra(ini + 6);
			}
			
			if(valorini != null) {
				AS.pintaEspacios(ini);
				System.out.println("valor inicial");
				valorini.muestra(ini + 3);
			}
		}
		
		else {
			AS.pintaEspacios(ini);
			System.out.println(nombre);
			
			if(n != null) {
				AS.pintaEspacios(ini + 3);
				System.out.println("mi referencia es ");
				n.muestra(ini + 6);
			}
		}
	}
}
