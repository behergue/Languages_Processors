package asem;

import java.util.List;

import ast.*;
import errors.GestionErroresTiny;

public class TipoExpresiones {
	
	// comprobaci�n de tipos para expresiones
	// devuelve el tipo de la expresi�n
	public Tipo tipoExpresiones(NodoArbol n) {
		
		switch (n.tipoNodo()) {
		
		// Expresiones binarias
		case EBIN:
			EBin ebin = (EBin) n;
			Tipo tipo1;
			Tipo tipo2;
			
			switch (ebin.tipo()) {
			
			// And
			case AND:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("bool") && tipo1.getNumDim() == 0 
						&& tipo2.getNombre().equals("bool") && tipo2.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el operador and: uno de los operandos no es un booleano");
				}
				
				break;
				
			// Acceso a array
			case CORCHETES:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNumDim() > 0 && tipo2.getNombre().equals("int") && tipo2.getNumDim() == 0) {
					return new Tipo(tipo1.getNombre(), tipo1.getNumDim() - 1, tipo1.getFila(), tipo1.getCol());
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el acceso a un array");
				}
				
				break;
				
			// Distinto
			case DIST:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals(tipo2.getNombre()) && tipo1.getNumDim() == 0 &&
						tipo2.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el distinto");
				}
				
				break;
				
			// Divisi�n entera
			case DIVE:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int")) {
					return new Tipo("int");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en la divisi�n entera");
				}
				
				break;
				
			// Divisi�n decimal
			case DIVD:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1 == tipo2 && (tipo1.getNombre().equals("int") || tipo1.getNombre().equals("float"))) {
					return new Tipo("int");
				} 
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en la divisi�n decimal");
				}
				
				break;
				
			// Igual igual
			case IGIG:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals(tipo2.getNombre()) && tipo1.getNumDim() == 0 &&
						tipo2.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el igual igual");
				}
				
				break;
				
			// Mayor
			case MAYOR:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int") &&
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el mayor");
				}
				
				break;
				
			// Mayor o igual
			case MAI:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int") && 
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el mayor o igual");
				}
				
				break;
				
			// Menor
			case MENOR:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int") &&
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el menor");
				}
				
				break;
				
			// Menor o igual
			case MEI:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int") &&
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el menor o igual");
				}
				
				break;
				
			// M�dulo
			case MOD:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int") && 
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("int");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el m�dulo");
				}
				
				break;
				
			// Multiplicaci�n
			case MUL:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int") &&
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("int");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en la multiplicaci�n");
				}
				
				break;
				
			// Or
			case OR:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("bool") && tipo2.getNombre().equals("bool") && 
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el or");
				}
				
				break;
				
			// Acceso a campo de struct
			case PUNTO:
				tipo1 = tipoExpresiones(ebin.opnd1());
				
				if (tipo1.getNombre().equals("user")) {
					Punto punto = (Punto) ebin;
					punto.setTipo(tipo1);
					Iden opnd2 = (Iden) ebin.opnd2();
					
					for (Instruccion ins : ((InsStruct) tipo1.getN()).getCuerpo()) {
						if (((Iden) ((InsDec) ins).getIden()).getId().equals(opnd2.getId())) {
							return ((InsDec) ins).getTipo();
						}
					}
					
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el acceso a un struct: no existe el campo al que "
							+ "se est� intentando acceder");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en el acceso a un struct: se est� intentando acceder a "
							+ "un campo de un tipo que no es struct");
				}
				
				break;
				
			// Resta
			case RESTA:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int") &&
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("int");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en la resta");
				}
				
				break;
				
			// Suma
			case SUMA:
				tipo1 = tipoExpresiones(ebin.opnd1());
				tipo2 = tipoExpresiones(ebin.opnd2());
				
				if (tipo1.getNombre().equals("int") && tipo2.getNombre().equals("int") &&
						tipo1.getNumDim() == 0 && tipo2.getNumDim() == 0) {
					return new Tipo("int");
				}
				
				else {
					GestionErroresTiny.errorSemantico(ebin.getFila(), ebin.getCol(), 
							"Error de tipos en la suma");
				}
				
				break;
				
			default:
				
				break;
			}
			break;
		
		// Expresiones unarias
		case EUN:
			EUn eunaria = (EUn) n;
			Tipo tipo =tipoExpresiones(eunaria.opnd1());
			
			switch (eunaria.tipo()) {
			
			// Acceso a un puntero
			case ASTERISCO:
				Tipo tipoAcceso = tipoExpresiones(eunaria.opnd1());
				
				if (tipoAcceso.isEsPuntero()) {
					Tipo a = new Tipo(tipoAcceso.getNombre(), tipoAcceso.getNumDim(), 
							tipoAcceso.getFila(), tipoAcceso.getCol());
					a.setEsPuntero(false);
					
					return a;
				}
				
				else {
					GestionErroresTiny.errorSemantico(eunaria.getFila(), eunaria.getCol(),
							"Error de tipos en un acceso a puntero");
				}
				
				break;

			// Menos
			case MENOS:
				if (tipo.getNombre().equals("int") && tipo.getNumDim() == 0) {
					return new Tipo("int");
				}
				
				else {
					GestionErroresTiny.errorSemantico(eunaria.getFila(), eunaria.getCol(), 
							"Error de tipos en el operador menos");
				}
				
				break;
				
			// M�s
			case MAS:
				if (tipo.getNombre().equals("int") && tipo.getNumDim() == 0) {
					return new Tipo("int");
				}
				
				else {
					GestionErroresTiny.errorSemantico(eunaria.getFila(), eunaria.getCol(), 
							"Error de tipos en el operador m�s");
				}
				
				break;
				
			// Negaci�n
			case NEG:
				if (tipo.getNombre().equals("bool") && tipo.getNumDim() == 0) {
					return new Tipo("bool");
				}
				
				else {
					GestionErroresTiny.errorSemantico(eunaria.getFila(), eunaria.getCol(), 
							"Error de tipos en la negaci�n");
				}
				break;
				
			default:
				
				break;
			}
			
			break;
		
		// Expresiones que no son binarias ni unarias
		case EXP:
			E exp = (E) n;
			
			switch (exp.tipo()) {
			
			// Car�cter
			case CAR:
				return new Tipo("char");
				
			// Entero
			case ENT:
				return new Tipo("int");
				
			// False
			case FALSO:
				return new Tipo("bool");
				
			// Identificador
			case IDEN:
				return ((InsDec) ((Iden) exp).getN()).getTipo();
				
			// Llamada a funci�n
			case LLAMADAFUNC:
				LlamadaFunc llamada = (LlamadaFunc) exp;
				
				if(((Instruccion) llamada.getN()).tipo() != TipoI.FUNC) {
					GestionErroresTiny.errorSemantico(exp.getFila(), exp.getCol(), 
							"Error de tipos en una llamada a funci�n: la llamada realizada no es a una funci�n");
					break;
				}
				
				List<Instruccion> parametros = ((InsDecFunc) llamada.getN()).getAtributos();
				
				if (parametros.size() == llamada.getArgs().size()) {
					boolean coincide = true;
					
					for (int i = 0; i < parametros.size(); i++) {
						
						coincide = coincide && ((InsDec) parametros.get(i)).getTipo().getNombre().
								equals(tipoExpresiones(llamada.getArgs().get(i)).getNombre());
					}
					
					if (!coincide) {
						GestionErroresTiny.errorSemantico(exp.getFila(), exp.getCol(), 
								"Error de tipos en una llamada a funci�n: tipo de alguno de los argumentos incorrecto");
					}
				}
				
				else {
					GestionErroresTiny.errorSemantico(exp.getFila(), exp.getCol(), 
							"Error de tipos en una llamada a funci�n: n�mero de argumentos incorrecto");
				}
				
				return ((InsDecFunc) llamada.getN()).getTipo();
				
			// Float
			case REAL:
				return new Tipo("float");
				
			// True
			case VERDAD:
				return new Tipo("bool");
				
			default:
				
				break;
			}
			
			break;
			
		default:
			
			break;

		}
		
		return new Tipo("error");
	}
}
