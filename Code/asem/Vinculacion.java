package asem;

import ast.*;
import errors.GestionErroresTiny;

public class Vinculacion {
	private TablaDeSimbolos tabla;

	public Vinculacion(TablaDeSimbolos tabla) {
		this.tabla = tabla;
	}

	public void vincula(NodoArbol n) {
		
		switch (n.tipoNodo()) {
		
		// case
		case CASE:
			Case caso = (Case) n;
			
			tabla.open();
			
			if(caso.getValor() != null) {
				vincula(caso.getValor());
			}
			
			for (Instruccion i : caso.getCuerpo()) {
				vincula(i);
			}
			
			tabla.close();
			
			break;
		
		// expresiones binarias
		case EBIN:
			EBin ebin = (EBin) n;
			
			if (ebin.tipo() == TipoE.PUNTO)
				vincula(ebin.opnd1());
			
			else {
				vincula(ebin.opnd1());
				vincula(ebin.opnd2());
			}
			
			break;
			
		// expresiones unarias
		case EUN:
			EUn eun = (EUn) n;
			
			vincula(eun.opnd1());
			
			break;
			
		// expresiones que no son ni binarias ni unarias
		case EXP:
			E exp = (E) n;
			
			// identificador
			if (exp.tipo() == TipoE.IDEN) {
				Iden id = (Iden) exp;
				
				NodoArbol ref = tabla.getDec(id.getId());
				
				if (ref == null) {
					GestionErroresTiny.errorSemantico(n.getFila(), n.getCol(), "La variable " + 
							id.getId() + " no ha sido declarada");
				}
					
				else {
					id.setN(ref);
				}

			// llamada a función
			} else if (exp.tipo() == TipoE.LLAMADAFUNC) {
				LlamadaFunc llamadafunc = (LlamadaFunc) exp;
				
				NodoArbol ref = tabla.getDec(((Iden) llamadafunc.getIden()).getId());
				
				llamadafunc.setN(ref);
				
				if (ref == null) {
					GestionErroresTiny.errorSemantico(n.getFila(), n.getCol(), "La función " + 
							((Iden) llamadafunc.getIden()).getId() + " no ha sido declarada");
				}
				
				for (E i : llamadafunc.getArgs()) {
					vincula(i);
				}
			}
			
			break;
		
		// instrucciones
		case INS:
			Instruccion ins = (Instruccion) n;
			
			switch (ins.tipo()) {
			
			// asignación
			case ASIG:
				InsAsig insasig = (InsAsig) ins;
				
				vincula(insasig.getIden());
				vincula(insasig.getValor());
				
				break;
				
			// declaración
			case DEC:
				InsDec insdec = (InsDec) ins;
				
				vincula(insdec.getTipo());
				
				tabla.addId(((Iden) insdec.getIden()).getId(), insdec);
				
				if (insdec.getValor() != null) {
					vincula(insdec.getValor());
				}
				
				break;
				
			// for
			case FOR:
				InsFor insfor = (InsFor) ins;
				
				tabla.open();
				
				vincula(insfor.getAsig());
				
				if (insfor.getAsig().tipo() == TipoI.ASIG) {
					NodoArbol refFor = tabla.getDec(((Iden) ((InsAsig) insfor.getAsig()).getIden()).getId());
					insfor.setVariableBucle(refFor);
					
					if (refFor == null) {
						GestionErroresTiny.errorSemantico(n.getFila(), n.getCol(), "La variable "
								+ ((Iden) ((InsAsig) insfor.getAsig()).getIden()).getId() + " no ha sido declarada");
					}	
				} 
				
				else {
					NodoArbol refFor2 = tabla.getDec(((Iden) ((InsDec) insfor.getAsig()).getIden()).getId());
					insfor.setVariableBucle(refFor2);
				}
				
				vincula(insfor.getCond());
				vincula(insfor.getPaso());
				
				for(Instruccion i : insfor.getCuerpo()) {
					vincula(i);
				}
				
				tabla.close();

				break;
			
			// declaración de función
			case FUNC:
				InsDecFunc insfunc = (InsDecFunc) ins;
				
				vincula(insfunc.getTipo());
				
				tabla.addId(((Iden) insfunc.getNombre()).getId(), insfunc);
				
				tabla.open();
				
				for (Instruccion i : insfunc.getAtributos()) {
					vincula(i);
				}
				
				for (Instruccion i : insfunc.getCuerpo()) {
					vincula(i);
				}
				
				vincula(insfunc.getDevuelve());
				
				tabla.close();
				
				break;
			
			// if
			case IF:
				InsIf insif = (InsIf) ins;
				
				vincula(insif.getCondicion());
				
				tabla.open();
				
				for(Instruccion i : insif.getListaif()) {
					vincula(i);
				}
				
				tabla.close();
				
				if (insif.getListaelse() != null) {
					
					tabla.open();
					
					for(Instruccion i : insif.getListaif()) {
						vincula(i);
					}
					
				    tabla.close();
				}
				
				break;
				
			// llamada a procedimiento
			case LLAMADAPROC:
				InsLlamadaProc insllamadaproc = (InsLlamadaProc) ins;
				
				NodoArbol ref = tabla.getDec(((Iden) insllamadaproc.getIden()).getId());
				
				if (ref == null) {
					GestionErroresTiny.errorSemantico(n.getFila(), n.getCol(), "El procedimiento " +
							((Iden) insllamadaproc.getIden()).getId() + " no ha sido declarado");
				}
				
				else {
					insllamadaproc.setN(ref);
				}
				
				for (E i : insllamadaproc.getLista()) {
					vincula(i);
				}
				
				break;
			
			// mas mas
			case MASMAS:
				InsMasMas masmas = (InsMasMas) ins;
				vincula(masmas.getV());
				break;
				
			// menos menos
			case MENOSMENOS:
				InsMenosMenos menosmenos = (InsMenosMenos) ins;
				vincula(menosmenos.getV());
				break;
				
			// declaración de procedimiento
			case PROC:
				InsDecProc insproc = (InsDecProc) ins;
				
				tabla.addId(((Iden) insproc.getNombre()).getId(), insproc);
				
				tabla.open();
				
				for (Instruccion i : insproc.getAtributos()) {
					vincula(i);
				}
				
				for (Instruccion i : insproc.getCuerpo()) {
					vincula(i);
				}
				
				tabla.close();
				
				break;
				
			// struct
			case STRUCT:
				InsStruct insstruct = (InsStruct) ins;
				
				tabla.addId(((Iden) insstruct.getNombretipo()).getId(), insstruct);
				
				tabla.open();
				
				for (Instruccion istruct : insstruct.getCuerpo()) {
					vincula(istruct);
				}
				
				tabla.close();
				
				break;
				
			// switch
			case SWITCH:
				InsSwitch insswitch = (InsSwitch) ins;
				
				NodoArbol refs = tabla.getDec(((Iden) insswitch.getVariable()).getId());
				insswitch.setVbleSwitch(refs);
				vincula(insswitch.getVariable());
				
				if (refs == null) {
					GestionErroresTiny.errorSemantico(n.getFila(), n.getCol(), "La variable " + 
									((Iden) insswitch.getVbleSwitch()).getId() + " no ha sido declarada");
				}
				
				for (Case i : insswitch.getCases()) {
					i.setRef(refs);
					vincula(i);
				}
				
				break;
				
			// while
			case WHILE:
				InsWhile inswhile = (InsWhile) ins;
				
				vincula(inswhile.getCondicion());
				
				tabla.open();
				
				for (Instruccion i : inswhile.getCuerpo()) {
					vincula(i);
				}
				
				tabla.close();
				
				break;
				
			default:
				
				break;
			}
			
			break;
		
		// tipo
		case TIPO:
			Tipo tipo = (Tipo) n;
			
			if (tipo.getNombre().equals("user")) {
				
				NodoArbol refu = tabla.getDec(tipo.getNombreStruct());
				
				tipo.setN(refu);
				
				if (refu == null) {
					GestionErroresTiny.errorSemantico(n.getFila(), n.getCol(), "El tipo " 
							+ tipo.getNombreStruct() + " no ha sido declarado");
				}
			}
			
			break;
			
		default:
			
			break;
		}
	}
}