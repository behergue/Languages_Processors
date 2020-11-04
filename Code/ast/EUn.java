package ast;

public abstract class EUn extends E {
	protected E opnd;
	
	public EUn(E opnd) {
		this.opnd = opnd;
	}
	   
	public E opnd1() {
		return opnd;
	}
	
	public TipoN tipoNodo() {return TipoN.EUN;}
	   
	protected void muestraOpnd1(int ini){
		opnd.muestra(ini);
	}
}