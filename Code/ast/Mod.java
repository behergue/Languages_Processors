package ast;

public class Mod extends EBin {
	
	public Mod(E opnd1, E opnd2, int fila, int col) {
		super(opnd1,opnd2);  
	}
	
	public TipoE tipo() {return TipoE.MOD;}
   
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("módulo");
		muestraOpnd1(ini + 3);
		muestraOpnd2(ini + 3);
	}
}
