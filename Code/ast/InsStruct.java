package ast;

import java.util.List;

public class InsStruct extends Instruccion{
	private E nombretipo;
	private List<Instruccion> cuerpo;

	public InsStruct(E nombretipo, List<Instruccion> cuerpo, int fila, int col) {
		super();
		this.nombretipo = nombretipo;
		this.cuerpo = cuerpo;
		this.fila = fila;
	    this.col = col;
	}
	
	public E getNombretipo() {
		return nombretipo;
	}

	public List<Instruccion> getCuerpo() {
		return cuerpo;
	}

	public TipoI tipo() {return TipoI.STRUCT;}
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("struct");
		
		AS.pintaEspacios(ini + 3);
		System.out.println("nombre");
		nombretipo.muestra(ini + 6);
		
		AS.pintaEspacios(ini + 3);
		System.out.println("cuerpo del struct");
		
		for(Instruccion i : cuerpo) {
			i.muestra(ini + 6);
		}
	}
}
