package ast;

import java.util.List;

public class InsSwitch extends Instruccion{
	private E variable;
	private List<Case> cases;
	private NodoArbol vbleSwitch;

	public InsSwitch(E variable, List<Case> cases, int fila, int col) {
		super();
		this.variable = variable;
		this.cases = cases;
		this.fila = fila;
	    this.col = col;
	}
	
	public NodoArbol getVbleSwitch() {
		return vbleSwitch;
	}

	public void setVbleSwitch(NodoArbol vbleSwitch) {
		this.vbleSwitch = vbleSwitch;
	}

	public E getVariable() {
		return variable;
	}

	public List<Case> getCases() {
		return cases;
	}

	public TipoI tipo() {return TipoI.SWITCH;}
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("switch");
		
		AS.pintaEspacios(ini + 3);
		System.out.println("variable");
		variable.muestra(ini + 6);

		AS.pintaEspacios(ini + 3);
		System.out.println("cases");
		
		for(Case i : cases) {
			i.muestra(ini + 6);
		}
	}
}
