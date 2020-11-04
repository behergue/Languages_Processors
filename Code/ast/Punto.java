package ast;

public class Punto extends EBin {
	private Tipo tipo;

	public Punto(E opnd1, E opnd2, int fila, int col) {
		super(opnd1,opnd2);  
		this.fila = fila;
		this.col = col;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
   
	public TipoE tipo() {return TipoE.PUNTO;}
   
	public void muestra(int ini) {
	   AS.pintaEspacios(ini);
	   System.out.println("acceso a struct");
	   muestraOpnd1(ini + 3);
	   AS.pintaEspacios(ini + 3);
	   System.out.println("campo");
	   muestraOpnd2(ini + 6);
	}
}
