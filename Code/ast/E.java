package ast;

public abstract class E extends NodoArbol{
	public abstract TipoE tipo(); 
	
	public E opnd1() {throw new UnsupportedOperationException("opnd1");} 
	public E opnd2() {throw new UnsupportedOperationException("opnd2");} 
   
	public abstract void muestra(int ini);
	public TipoN tipoNodo() {return TipoN.EXP;}
}