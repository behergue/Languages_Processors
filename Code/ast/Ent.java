package ast;

public class Ent extends E {
	private String v;
	
	public Ent(String v, int fila, int col) {
		this.v = v;   
		this.fila = fila;
		this.col = col;
	}
  
	public String getV() {
		return v;
	}
	
	public TipoE tipo() {return TipoE.ENT;}
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("entero: " + v);
	}
}
