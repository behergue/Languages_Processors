package ast;

public class Corchetes extends EBin {
	
	public Corchetes(E opnd1, E opnd2, int fila, int col) {
		super(opnd1,opnd2);  
		this.fila = fila;
		this.col = col;
	}   
   
	public TipoE tipo() {return TipoE.CORCHETES;}
   
	public void muestra(int ini) {
		muestraOpnd1(ini);
		AS.pintaEspacios(ini + 3);
		System.out.println("acceso array");
		AS.pintaEspacios(ini + 6);
		System.out.println("posición");
		muestraOpnd2(ini + 9);
	}
}
