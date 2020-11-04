package asem;

import java.util.List;

import ast.*;
import errors.GestionErroresTiny;

public class TipoInstrucciones {
	TipoExpresiones e = new TipoExpresiones();
	
	// comprobaci�n de tipos para instrucciones
	// devuelve un booleano que indica si los tipos son correctos
	public boolean tipoInstrucciones(NodoArbol n) {
			
		switch (n.tipoNodo()) {
			
		// case
		case CASE:
			Case caso = (Case) n;
				
			if(caso.getValor() == null) {
				boolean aux = true;
					
				for(Instruccion i : caso.getCuerpo()) {
					aux = aux && tipoInstrucciones(i);
				}
					
				return aux;
			}
				
			else if (((InsDec) caso.getRef()).getTipo().getNombre().equals
					(e.tipoExpresiones(caso.getValor()).getNombre())) {
				boolean aux = true;
					
				for(Instruccion i : caso.getCuerpo()) {
					aux = aux && tipoInstrucciones(i);
				}
					
				return aux;
			}
				
			else {
				GestionErroresTiny.errorSemantico(caso.getFila(), caso.getCol(),
						"Error de tipos en el case: el tipo del case es incorrecto");
			}
				
			break;
			
		// instrucciones
		case INS:
			Instruccion ins = (Instruccion) n;
				
			switch (ins.tipo()) {
				
			// asignaci�n
			case ASIG:
				InsAsig insAsig = (InsAsig) ins;
					
				if (insAsig.getIden().tipo() == TipoE.CORCHETES || insAsig.getIden().tipo() == TipoE.PUNTO ||
						insAsig.getIden().tipo() == TipoE.IDEN || insAsig.getIden().tipo() == TipoE.ASTERISCO ) {
					Tipo tipoDcha = e.tipoExpresiones(insAsig.getValor());
						
					if(tipoDcha.getNombre().equals("user")) {
						GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
								"Error de tipos en una asignaci�n: no es posible asignar un struct");
						return false;
					}
					
					else if (tipoDcha.getNumDim() > 0 && !tipoDcha.isEsPuntero()) {
						GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
								"Error de tipos en una asignaci�n: no es posible asignar un vector");
					}
						
					if (e.tipoExpresiones(insAsig.getIden()).getNombre().equals(tipoDcha.getNombre()) && 
							e.tipoExpresiones(insAsig.getIden()).getNumDim() == tipoDcha.getNumDim()) {
						return true;
					}
							
					else {
						GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
								"Error de tipos en una asignaci�n: no coinciden los tipos");
					}
						
				} 
					
				else {
					GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
							"Error de tipos en una asignaci�n: tipo no asignable");
				}
				
				break;
					
			// llamada a procedimiento
			case LLAMADAPROC:
				InsLlamadaProc llamada = (InsLlamadaProc) ins;
					
				if(((Instruccion) llamada.getN()).tipo() != TipoI.PROC) {
					GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
						"Error de tipos en una llamada a procedimiento: la llamada realizada no es a una procedimiento");
					break;
				}
				
				List<Instruccion> parametros = ((InsDecProc) llamada.getN()).getAtributos();
					
				if (parametros.size() == llamada.getLista().size()) {
					boolean coincide = true;
						
					for (int i = 0; i < parametros.size(); i++) {
						coincide = coincide && ((InsDec) parametros.get(i)).getTipo().getNombre().
								equals(e.tipoExpresiones(llamada.getLista().get(i)).getNombre());								;
					}
						
					if (!coincide) {
						GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
								"Error de tipos en una llamada a procedimiento: tipo de alguno de los argumentos incorrecto");
					}
	
					return coincide;
				}
					
				GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
						"Error de tipos en una llamada a procedimiento: n�mero de argumentos incorrecto");
	
				break;
					
			// if
			case IF:
				InsIf insCond = (InsIf) ins;
					
				if (e.tipoExpresiones(insCond.getCondicion()).getNombre().equals("bool")) {
					boolean correcto = true;
						
					for(Instruccion i : insCond.getListaif()) {
						correcto = correcto && tipoInstrucciones(i);
					}
						
					if (insCond.getListaelse() != null) {
						for(Instruccion i : insCond.getListaelse()) {
							correcto = correcto && tipoInstrucciones(i);
						}
					}
						
					return correcto;
				}
					
				GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
						"Error de tipos en el if: la condici�n no es una expresi�n booleana");
					
				break;
					
			// declaraci�n
			case DEC:
				InsDec insDec = (InsDec) ins;
					
				if (insDec.getValor() != null) {
					
					if(insDec.getValor().tipo() == TipoE.NEW) {
						insDec.getTipo().setEsPuntero(true);
						return true;
					}
						
					Tipo aux = e.tipoExpresiones(insDec.getValor());
					if (insDec.getTipo().getNombre().equals(aux.getNombre()) &&
							insDec.getTipo().getNumDim() == aux.getNumDim()) {				
							
						return true;
					}
						
					else {
						GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
								"Error de tipos en una declaraci�n: tipo del valor inicial incorrecto");
					}
									
				}
				else
						
					return true;
					
				break;
					
			// for
			case FOR:
				InsFor insFor = (InsFor) ins;
				boolean okfor = tipoInstrucciones(insFor.getAsig());
				
				if(!okfor) {
					GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
							"Error de tipos en el for: tipo de la asignaci�n incorrecto");
					break;
				}
				
				okfor = okfor	&& e.tipoExpresiones(insFor.getCond()).getNombre().equals("bool");
				
				if(!okfor) {
					GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
							"Error de tipos en el for: tipo de la condici�n incorrecto");
					break;
				}
				
				okfor = okfor	&& tipoInstrucciones(insFor.getPaso());
				
				if(!okfor) {
					GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
							"Error de tipos en el for: tipo del paso incorrecto");
					break;
				}
				
				for(Instruccion i : insFor.getCuerpo()) {
					okfor = okfor && tipoInstrucciones(i);
				}
				
				return true;
				
			// declaraci�n de funci�n
			case FUNC:
				InsDecFunc insDecFunc = (InsDecFunc) ins;
				boolean okdecfunc = true;
				
				for(Instruccion i : insDecFunc.getCuerpo()) {
					okdecfunc = okdecfunc && tipoInstrucciones(i);
				}
				
				if(!okdecfunc) {
					return false;
				}
				
				okdecfunc = insDecFunc.getTipo().getNombre().equals(e.tipoExpresiones(insDecFunc.getDevuelve()).getNombre());
				
				if(!okdecfunc) {
					GestionErroresTiny.errorSemantico(n.getFila(), n.getCol(), 
							"Error de tipos en una declaraci�n de funci�n: tipo del return incorrecto");
				}
				
				return okdecfunc;
				
			// m�s m�s
			case MASMAS:
				InsMasMas masmas = (InsMasMas) ins;
				return e.tipoExpresiones(masmas.getV()).getNombre().equals("int");
					
			// menos menos
			case MENOSMENOS:
				InsMenosMenos menosmenos = (InsMenosMenos) ins;
				return e.tipoExpresiones(menosmenos.getV()).getNombre().equals("int");
				
			// declaraci�n de procedimieno
			case PROC:
				InsDecProc insDecProc = (InsDecProc) ins;
				boolean okdecproc = true;
				
				for(Instruccion i : insDecProc.getCuerpo()) {
					okdecproc = okdecproc && tipoInstrucciones(i);
				}
				
				return okdecproc;
				
			// struct
			case STRUCT:
				InsStruct insStruct = (InsStruct) ins;
				boolean okstruct = true;
				
				for (Instruccion instr : insStruct.getCuerpo()) {
					okstruct = okstruct && tipoInstrucciones(instr);
				}
				
				return okstruct;
				
			// switch
			case SWITCH:
				InsSwitch insSwitch = (InsSwitch) ins;
				boolean okswitch = true;
				
				for (Case casoSwitch : insSwitch.getCases()) {
					okswitch = okswitch && tipoInstrucciones(casoSwitch);
				}
				
				return okswitch;
				
			// while
			case WHILE:
				InsWhile insWhile = (InsWhile) ins;
				boolean okwhile = true;
				
				if (e.tipoExpresiones(insWhile.getCondicion()).getNombre().equals("bool")) {
					
					for(Instruccion i : insWhile.getCuerpo()) {
						okwhile = okwhile && tipoInstrucciones(i);
					}
					
					return okwhile;
				}
				
				GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
						"Error de tipos en el while: la condici�n no es de tipo booleano");
				
				break;
				
			default:
				
				GestionErroresTiny.errorSemantico(ins.getFila(), ins.getCol(), 
						"Error de tipos: instrucci�n incorrecta");
				
				break;
			}
			
			break;
			
		default:
			
			GestionErroresTiny.errorSemantico(n.getFila(), n.getCol(), 
					"Error de tipos: instrucci�n incorrecta");
			
			break;
		}
		
		return false;
	}
}
