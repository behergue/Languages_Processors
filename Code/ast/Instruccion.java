package ast;

public abstract class Instruccion extends NodoArbol{
	public abstract TipoI tipo();
	public abstract void muestra(int ini);
	public TipoN tipoNodo() {return TipoN.INS;}
}
