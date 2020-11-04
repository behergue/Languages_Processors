package ast;

public class Car extends E {
	private String v;
	
	public Car(String v, int fila, int col) {
		this.v = v;   
		this.fila = fila;
		this.col = col;
	}
  
	public String getV() {
		return v;
	}
  
	public TipoE tipo() {return TipoE.CAR;}
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("carácter: " + v);
	}
}
