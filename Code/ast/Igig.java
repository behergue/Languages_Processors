package ast;

public class Igig extends EBin {
	
	public Igig(E opnd1, E opnd2, int fila, int col) {
		super(opnd1,opnd2);  
		this.fila = fila;
		this.col = col;
	}
   
	public TipoE tipo() {return TipoE.IGIG;}
   
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("comparación igual");
		muestraOpnd1(ini + 3);
		muestraOpnd2(ini + 3);
	}
}