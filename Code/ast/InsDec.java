package ast;

public class InsDec extends Instruccion{
	private Tipo tipo;
	private E iden;
	private E valor;

	public InsDec(Tipo tipo, E iden, E valor, int fila, int col) {
		super();
		this.tipo = tipo;
		this.iden = iden;
		this.valor = valor;
	    this.fila = fila;
	    this.col = col;
	}
	
	public InsDec(Tipo tipo, E iden, int fila, int col) {
		super();
		this.tipo = tipo;
		this.iden = iden;
	    this.fila = fila;
	    this.col = col;
	}
	
	public void setValor(E valor) {
		this.valor = valor;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public E getIden() {
		return iden;
	}

	public E getValor() {
		return valor;
	}

	public TipoI tipo() {return TipoI.DEC;}

	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("declaración");
		
		AS.pintaEspacios(ini + 3);
		System.out.println("tipo");
		tipo.muestra(ini + 6);
		
		AS.pintaEspacios(ini + 3);
		System.out.println("nombre");
		iden.muestra(ini + 6);
		
		if(valor != null) {
			AS.pintaEspacios(ini + 3);
			System.out.println("valor");
			valor.muestra(ini + 6);
		}
	}
}
