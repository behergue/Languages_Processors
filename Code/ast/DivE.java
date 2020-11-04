package ast;

public class DivE extends EBin {
	
	public DivE(E opnd1, E opnd2, int fila, int col) {
		super(opnd1,opnd2);
		this.fila = fila;
		this.col = col;
	}    
   
	public TipoE tipo() {return TipoE.DIVE;}
   
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("división entera");
		muestraOpnd1(ini + 3);
		muestraOpnd2(ini + 3);
	}
}
