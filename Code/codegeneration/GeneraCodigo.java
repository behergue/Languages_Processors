package codegeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ast.*;
import javafx.util.Pair;

public class GeneraCodigo {
	// Lista de ámbitos abiertos
	private List<Ambito> ambitosAbiertos = new ArrayList<>();
	// Lista con el código maquina que hay que pintar
	private List<InsCodigoP> codigoP = new ArrayList<>();
	// Ámbito actual
	private Ambito ambitoActual = null;
	// Fichero para la salida
	private File fich = new File("code.txt");
	// Altura o nivel del ámbito abierto
	private int alturaAmbito = 0;
	// Máxima profundidad a la que hemos llegado
	private int maxProfundidad = 0;
	// Máxima dirección asignada
	private int maxDir = 5;
	
	public void generaCodigo(List<Instruccion> raiz) {
		PrintWriter pw;
		try {
			fich.createNewFile();

			pw = new PrintWriter(fich);
			
			open(true);
			
			for (Instruccion i : raiz)
				asignaDir(i);
			
			close();
			
			addIns("ssp " + ambitosAbiertos.get(0).getSsp(), 0);
			addIns("sep ", 0);
			codigoFuncProc(raiz);
		
			int tam = tamPila(1);
			codigoP.get(1).setName(codigoP.get(1).getName() + tam);
			addIns("stp", 0);
			write();
			
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void asignaDir(NodoArbol n) {
		switch (n.tipoNodo()) {
		
		case CASE:
			open();
			
			Case caso = (Case) n;
			for(Instruccion i: caso.getCuerpo()) {
				asignaDir(i);
			}
			
			close();
			
			break;
			
		case INS:
			Instruccion ins = (Instruccion) n;
			
			switch (ins.tipo()) {
				
			case DEC:
				InsDec insdec = (InsDec) ins;
				((Iden) insdec.getIden()).setProfundidad(ambitoActual.getProfundidad() + 1);
				
				if (insdec.getTipo().getNombre().equals("user")) {
					addId(((Iden) insdec.getIden()).getId(), ambitoActual.tam(insdec.getTipo().getNombreStruct()));
					addDir(insdec, ambitoActual.dirVar(((Iden) insdec.getIden()).getId()));
				} 
				
				else if (insdec.getTipo().getNumDim() > 0) {
					
					if(insdec.getTipo().getNombre().equals("user")) {
						insdec.getTipo().getLista().add(new Ent(((Integer) 
								ambitoActual.tam(insdec.getTipo().getNombreStruct())).toString(), 0, 0));
					}
					
					else {
						insdec.getTipo().getLista().add(new Ent("1", 0, 0));
					}
					
					ambitoActual.addDim(((Iden) insdec.getIden()).getId(), tamArray(insdec));
					
					int aux = 1;
					for(E e: insdec.getTipo().getLista()) {
						aux *= Integer.parseInt(((Ent) e).getV());
					}
					
					addId(((Iden) insdec.getIden()).getId(), aux);
					ambitoActual.addTipo(((Iden) insdec.getIden()).getId(), aux);
				} 
				
				else if (insdec.getTipo().isEsPuntero() && insdec.getValor() != null && 
						insdec.getValor().tipo() == TipoE.NEW) {
					addId(((Iden) insdec.getIden()).getId(), 1);
					New nuevo = ((New) insdec.getValor());
					
					if (insdec.getTipo().getNumDim() > 0) {
						
						List<Integer> tamDims = new ArrayList<>();
						for (E dim : insdec.getTipo().getLista()) {
							tamDims.add(Integer.parseInt(((Ent) dim).getV()));
						}
						
						int aux = 1;
						for (int dim : tamDims) {
							aux *= dim;
						}
						
						if ((insdec.getTipo().getNombre().equals("user"))) {
							aux *= ambitoActual.tam(((insdec.getTipo()).getNombreStruct()));
						}
						
						nuevo.setTamTotal(aux);
						
						List<Integer> dims = new ArrayList<>(tamDims);
						dims.add(tamTipo(insdec.getTipo()));
						ambitoActual.addDim(((Iden) insdec.getIden()).getId(), dims);
					} 
					
					else if (insdec.getTipo().getNombre().equals("user")) {
						nuevo.setTamTotal(ambitoActual.tam(insdec.getTipo().getNombreStruct()));
					} 
					
					else {
						nuevo.setTamTotal(1);
					}
				} 
				
				else {
					
					if (insdec.getTipo().isEsPuntero() && insdec.getValor() != null && 
							insdec.getTipo().getNumDim() > 0 && insdec.getValor().tipo() == TipoE.IDEN) {
						ambitoActual.addDim(((Iden) insdec.getIden()).getId(), 
								ambitoActual.tamArray(((Iden) insdec.getValor()).getId()));
					}
					
					addId(((Iden) insdec.getIden()).getId(), 1);
				}
				
				break;
				
			case FOR:
				open();
				
				InsFor insfor = (InsFor) ins;
				Instruccion decIni = insfor.getAsig();
				asignaDir(decIni);
				
				for (Instruccion instFor : insfor.getCuerpo()) {
					asignaDir(instFor);
				}
				
				close();
				
				break;
				
			case FUNC:
				
				open(true);
				
				InsDecFunc insdecfunc = (InsDecFunc) ins;
				insdecfunc.setProfundidad(ambitoActual.getProfundidad());
				
				for (Instruccion i : insdecfunc.getAtributos()) {
					asignaDir(i);
				}
				
				for (Instruccion i : insdecfunc.getCuerpo()) {
					asignaDir(i);
				}
				
				close();
				
				break;
				
			case IF:
				open();
				InsIf insif = (InsIf) ins;
				
				for(Instruccion i: insif.getListaif()) {
					asignaDir(i);
				}
				
				close();
				
				if (insif.getListaelse() != null) {
					open();
					
					for(Instruccion i: insif.getListaelse()) {
						asignaDir(i);
					}
					
					close();
				}
				
				break;
				
			case PROC:
				open(true);
				
				InsDecProc insdecproc = (InsDecProc) ins;
				insdecproc.setProfundidad(ambitoActual.getProfundidad());
				
				for (Instruccion i : insdecproc.getAtributos()) {
					asignaDir(i);
				}
				
				for (Instruccion i : insdecproc.getCuerpo()) {
					asignaDir(i);
				}
				
				close();
				
				break;
				
			case STRUCT:
				InsStruct insstruct = (InsStruct) ins;
				int aux = 0;
				
				for (Instruccion i : insstruct.getCuerpo()) {
					InsDec insd = (InsDec) i;
					ambitoActual.addCampo(((Iden) insstruct.getNombretipo()).getId() + "." + 
					((Iden) insd.getIden()).getId(), aux);
					
					if (insd.getTipo().getNombre().equals("user")) {
						aux += ambitoActual.tam(insd.getTipo().getNombreStruct());
					}
					
					else if (insd.getTipo().getNumDim() > 0) {
						int aux2 = 1;
						
						for(E e: insd.getTipo().getLista()) {
							aux2 *= Integer.parseInt(((Ent) e).getV());
						}
						
						aux += aux2;
						ambitoActual.addTipo(((Iden) insstruct.getNombretipo()).getId() + "." + 
						((Iden) insd.getIden()).getId(), aux2);
					} 
					
					else if (insd.getTipo().isEsPuntero() && insd.getValor() != null && 
							insd.getValor().tipo() == TipoE.NEW) {
						New nuevo = ((New) insd.getValor());
						
						if (insd.getTipo().getNumDim() > 0) {
							
							List<Integer> tamDims = new ArrayList<>();
							for (E dim : insd.getTipo().getLista()) {
								tamDims.add(Integer.parseInt(((Ent) dim).getV()));
							}
							
							int aux3 = 1;
							for (int dim : tamDims) {
								aux3 *= dim;
							}
							
							if (insd.getTipo().getNombre().equals("user")) {
								aux3 *= ambitoActual.tam(insd.getTipo().getNombreStruct());
							}
							
							nuevo.setTamTotal(aux3);
							List<Integer> dims = new ArrayList<>(tamDims);
							dims.add(tamTipo(insd.getTipo()));
							ambitoActual.addDim(((Iden) insd.getIden()).getId(), dims);
						} 
						
						else if (insd.getTipo().getNombre().equals("user")) {
							nuevo.setTamTotal(ambitoActual.tam(insd.getTipo().getNombreStruct()));
						} 
						
						else {
							nuevo.setTamTotal(1);
						}
						
						aux++;
					}
					
					else if (insd.getTipo().isEsPuntero() && insd.getValor() != null && 
							insd.getTipo().getNumDim() > 0 && insd.getValor().tipo() == TipoE.IDEN) {
						ambitoActual.addDim(((Iden) insd.getIden()).getId(), 
								ambitoActual.tamArray(((Iden) insd.getValor()).getId()));
						aux++;
					}
					
					else {
						aux++;
					}

				}
				
				ambitoActual.addTipo(((Iden) insstruct.getNombretipo()).getId(), aux);
				
				break;
				
			case SWITCH:
				InsSwitch insswitch = (InsSwitch) ins;
				
				for (Case casoSwitch : insswitch.getCases()) {
					asignaDir(casoSwitch);
				}
				
				break;
				
			case WHILE:
				open();
				
				InsWhile inswhile = (InsWhile) ins;
				for(Instruccion i : inswhile.getCuerpo()) {
					asignaDir(i);
				}
				
				close();
				
				break;
				
			default:
				
				break;
			}
			
			break;
			
		default:
			
			break;
		}
	}

	private void addDir(InsDec insdec, int dir) {
		if (insdec.getTipo().getNombre().equals("user")) {
			
			for (Instruccion i : ((InsStruct) insdec.getTipo().getN()).getCuerpo()) {
				InsDec insDec1 = (InsDec) i;
				
				int aux =  + ambitoActual.dirVar(insdec.getTipo().getNombreStruct() + "." + 
				((Iden) insDec1.getIden()).getId());
				
				addDir(new InsDec(insDec1.getTipo(), new Iden(((Iden) insdec.getIden()).getId() + "." + 
				((Iden) insDec1.getIden()).getId(), 0, 0), insDec1.getValor(), 0, 0), aux);
			}
			
			ambitoActual.addTipo(((Iden) insdec.getIden()).getId(), ambitoActual.tam(insdec.getTipo().getNombreStruct()));
		} 
		
		else if (insdec.getTipo().getNumDim() > 0) {
			int aux = 1;
			
			for(E e: insdec.getTipo().getLista()) {
				aux *= Integer.parseInt(((Ent) e).getV());
			}
			
			ambitoActual.addTipo(((Iden) insdec.getIden()).getId(), aux);
			ambitoActual.addCampo(((Iden) insdec.getIden()).getId(), dir);
			ambitoActual.addDim(((Iden) insdec.getIden()).getId(), tamArray(insdec));
		} 
		
		else {
			ambitoActual.addCampo(((Iden) insdec.getIden()).getId(), dir);
		}
	}

	// Nota: no se han implementado los casos de carácter, real y 
	// división decimal porque no sería posible probarlos en la máquina P
	private void codigoExpresion(E exp) {
		
		switch (exp.tipo()) {
		
		case AND:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("and", -1);
			
			break;
		
		case ASTERISCO:
			codigoExpresionEspecial(exp);
			addIns("ind", 0);
			
			break;
			
		case CORCHETES:
			codigoExpresionEspecial(exp);
			addIns("ind", 0);
			
			break;
			
		case DIST:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("neq", -1);
			
			break;
			
		case DIVE:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("div", -1);
			
			break;
			
		case ENT:
			addIns("ldc " + ((Ent) exp).getV(), 1);
			
			break;

		case FALSO:
			addIns("ldc false", 1);
			
			break;

		case IDEN:
			
			if (((InsDec)((Iden) exp).getN()).getTipo().getNombre().equals("user")) {
				
				for (Instruccion i : ((InsStruct) ((InsDec)((Iden) exp).getN()).getTipo().getN()).getCuerpo()) {
					Iden id = new Iden(((Iden) exp).getId() + "." + ((Iden) ((InsDec) i).getIden()).getId(), 0, 0);
					id.setN(((InsDec) i).getTipo().getN());
					addIns("lda " + 0 + " " + ambitoActual().dirVar(id.getId()), 1);
					addIns("ind", 0);
				}
			} 
			
			else {
				codigoExpresionEspecial(exp);
				addIns("ind", 0);
			}
			
			break;
			
		case IGIG:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("equ", -1);
			
			break;
			
		case LLAMADAFUNC:
			LlamadaFunc llamadafunc = (LlamadaFunc) exp;
			
			int dir = ((InsDecFunc) llamadafunc.getN()).getDir();
			int tam = ((InsDecFunc) llamadafunc.getN()).getTam();
			int prof = ((InsDecFunc) llamadafunc.getN()).getProfundidad();
			int profAct = ambitoActual().getProfundidad() + 1;
			
			addIns("mst " + (profAct - prof), 5);
			
			codigoArgumentos(((InsDecFunc) llamadafunc.getN()).getAtributos(), llamadafunc.getArgs());
			
			addIns("cup " + tam + " " + dir, 0);
			
			break;
			
		case MAI:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("geq", -1);
			
			break;
			
		case MAS:
			codigoExpresion(exp.opnd1());
			
			break;
			
		case MAYOR:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("grt", -1);
			
			break;
			
		case MEI:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("leq", -1);
			
			break;
			
		case MENOR:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("les", -1);
			
			break;
			
		case MENOS:
			codigoExpresion(exp.opnd1());
			addIns("neg", 0);
			
			break;
			
		case MOD:
			codigoExpresion(new Resta(exp.opnd1(), new Mul(exp.opnd2(), new DivE(exp.opnd1(), 
					exp.opnd2(), 0, 0),0 , 0), 0, 0));
			
			break;
			
		case MUL:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("mul", -1);
			
			break;
			
		case NEG:
			codigoExpresion(exp.opnd1());
			addIns("not", 0);
			
			break;
			
		case NEW:
			New nuevo = (New) exp;
			addIns("ldc " + nuevo.getTamTotal(), 1);
			addIns("new", -2);
			
			break;
			
		case OR:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("or", -1);
			
			break;
			
		case PUNTO:
			codigoExpresionEspecial(exp);
			addIns("ind", 0);
			
			break;
			
		case RESTA:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("sub", -1);
			
			break;
			
		case SUMA:
			codigoExpresion(exp.opnd1());
			codigoExpresion(exp.opnd2());
			addIns("add", -1);
			
			break;
			
		case VERDAD:
			addIns("ldc true", 1);
			
			break;
			
		default:
			
			break;
		}
	}
	
	private Pair<List<Integer>, Integer> codigoExpresionEspecial(E exp) {
		Pair<List<Integer>, Integer> p = null;
		
		if (exp.tipo() == TipoE.IDEN) {
			Iden id = ((Iden) ((InsDec) ((Iden) exp).getN()).getIden());
			
			List<Integer> lista = ambitoActual().tamArray(((Iden) ((InsDec) ((Iden) exp).getN()).getIden()).getId());
			
			p = new Pair<>(lista, 0);
			
			addIns("lda " + (ambitoActual().getProfundidad() + 1 - id.getProfundidad()) + " "
					+ ambitoActual().dirVar(((Iden) exp).getId()), 1);
		} 
		
		else if (exp.tipo() == TipoE.CORCHETES) {
			p = codigoExpresionEspecial(exp.opnd1());
			codigoExpresion(exp.opnd2());
			
			int aux = 1;
			for (int i = p.getValue() + 1; i < p.getKey().size(); i++) {
				aux *= p.getKey().get(i);
			}
			
			addIns("chk 0 " + (p.getKey().get(p.getValue()) - 1), 0);
			addIns("ixa " + aux, -1);
			
			p = new Pair<>(p.getKey(), p.getValue() + 1);

		} 
		
		else if (exp.tipo() == TipoE.PUNTO) {
			p = codigoExpresionEspecial(exp.opnd1());
			
			for (Instruccion i : ((InsStruct) ((Punto) exp).getTipo().getN()).getCuerpo()) {
				InsDec insdec = (InsDec) i;
				
				if (((Iden) insdec.getIden()).getId().equals(((Iden) exp.opnd2()).getId())
						&& insdec.getTipo().getNumDim() > 0) {
					List<Integer> lista = tamArray(insdec);
					p = new Pair<>(lista, 0);
				}
			}
			
			int aux = ambitoActual().dirVar(((Punto) exp).getTipo().getNombreStruct() + "." 
			+ ((Iden) exp.opnd2()).getId());
			addIns("inc " + aux, 0);

		} 
		
		else if (exp.tipo() == TipoE.ASTERISCO) {
			p = codigoExpresionEspecial(exp.opnd1());
			addIns("ind", 0);
		}
		
		return p;
	}

	private void codigoInstruccion(Instruccion ins) {
		switch (ins.tipo()) {
		
		case ASIG:
			InsAsig insasig = (InsAsig) ins;
			
			codigoExpresionEspecial(insasig.getIden());
			codigoExpresion(insasig.getValor());
			addIns("sto", -2);
			
			break;
			
		case DEC:
			InsDec insdec = (InsDec) ins;
			
			if (insdec.getValor() != null) {
				
				if (insdec.getTipo().getNumDim() > 0) {
					ambitoActual().addDim(((Iden) insdec.getIden()).getId(), tamArray(insdec));
		
					if (insdec.getTipo().getNombre().equals("user")) {
						asignaVariosCampos((Iden) insdec.getIden(), insdec.getTipo(),
								ambitoActual().dirVar(((Iden) insdec.getIden()).getId()), -1,
								insdec.getTipo().getValorini(), 0);
					} 
					
					else {
						asignaVariosCampos((Iden) insdec.getIden(), insdec.getTipo(),
								ambitoActual().dirVar(((Iden) insdec.getIden()).getId()),
								ambitoActual().dirVar(stringAccesoStruct(insdec.getTipo().getValorini())),
								insdec.getTipo().getValorini(), 0);
					}

				} 
				
				else if (insdec.getTipo().getNombre().equals("user")) {
					insdec.setValor(null);
					codigoInstruccion(insdec);
					
					asignaVariosCampos((Iden) insdec.getIden(), insdec.getTipo(),
							ambitoActual().dirVar(((Iden) insdec.getIden()).getId()),
							ambitoActual().dirVar(stringAccesoStruct((insdec.getValor()))), null, 0);
				} 
				
				else if (insdec.getTipo().isEsPuntero() && insdec.getValor().tipo() == TipoE.NEW) {
					addIns("lda " + 0 + " " + ambitoActual().dirVar(((Iden) insdec.getIden()).getId()), 1);
					codigoExpresion(insdec.getValor());
				} 
				
				else {
					addIns("lda " + 0 + " " + ambitoActual().dirVar(((Iden) insdec.getIden()).getId()), 1);
					codigoExpresion(insdec.getValor());
					addIns("sto", -2);
				}
			} 
			
			else if (insdec.getTipo().getNombre().equals("user")) {
				for (Instruccion i : (((InsStruct) insdec.getTipo().getN()).getCuerpo())) {
					InsDec decStr = (InsDec) i;
					codigoInstruccion(new InsDec(decStr.getTipo(), new Iden(((Iden) insdec.getIden()).getId() + 
							"." + ((Iden) decStr.getIden()).getId(), 0, 0), decStr.getValor(), 0, 0));
				}
			}
			
			break;
			
		case FOR:
			maxProfundidad++;
			alturaAmbito = maxProfundidad;
			
			InsFor insFor = (InsFor) ins;
			codigoInstruccion(insFor.getAsig());
			int p1 = codigoP.size();
			codigoExpresion(insFor.getCond());
			
			int p2 = codigoP.size();
			addIns("fjp ", -1);
			codigoFuncProc(insFor.getCuerpo());
			codigoInstruccion(insFor.getPaso());
			addIns("ujp " + p1, 0);
			codigoP.get(p2).setName(codigoP.get(p2).getName() + codigoP.size());
			alturaAmbito = ambitoActual().getPadre().getPos();
			
			break;
			
		case FUNC:
			InsDecFunc insdecfunc = (InsDecFunc) ins;
			
			maxProfundidad++;
			alturaAmbito = maxProfundidad;
			insdecfunc.setDir(codigoP.size());
			
			for (Instruccion i : insdecfunc.getAtributos()) {
				
				if (((InsDec) i).getTipo().getNombre().equals("user")) {
					insdecfunc.incrementa(ambitoActual().tam(((InsDec) i).getTipo().getNombreStruct()));
				}
				
				else {
					insdecfunc.incrementa(1);
				}
			}
			
			addIns("ssp " + ambitoActual().getSsp(), 0);
			int psep = codigoP.size();
			addIns("sep ", 0);
			codigoFuncProc(insdecfunc.getCuerpo());
			addIns("lda 0 0", 1);
			codigoExpresion(insdecfunc.getDevuelve());
			addIns("sto", -2);
			
			int tampila = tamPila(psep);
			codigoP.get(psep).setName(codigoP.get(psep).getName() + tampila);
			addIns("retf", 0);
			alturaAmbito = ambitoActual().getPadre().getPos();
			
			break;
			
		case IF:
			InsIf insif = (InsIf) ins;
			
			codigoExpresion(insif.getCondicion());
			maxProfundidad++;
			alturaAmbito = maxProfundidad;
			
			int s1 = codigoP.size();
			addIns("fjp ", -1);
			codigoFuncProc(insif.getListaif());
			alturaAmbito = ambitoActual().getPadre().getPos();
			
			if (insif.getListaelse() != null) {
				maxProfundidad++;
				alturaAmbito = maxProfundidad;
				
				int s2 = codigoP.size();
				addIns("ujp ", 0);
				codigoP.get(s1).setName(codigoP.get(s1).getName() + codigoP.size());
				codigoFuncProc(insif.getListaelse());
				codigoP.get(s2).setName(codigoP.get(s2).getName() + codigoP.size());
				alturaAmbito = ambitoActual().getPadre().getPos();
			} 
			
			else {
				codigoP.get(s1).setName(codigoP.get(s1).getName() + codigoP.size());
			}
			
			break;
			
		case LLAMADAPROC:
			InsLlamadaProc insllamadaproc = (InsLlamadaProc) ins;
			
			int dir = ((InsDecProc) insllamadaproc.getN()).getDir();
			int tam = ((InsDecProc) insllamadaproc.getN()).getTam();
			int prof = ((InsDecProc) insllamadaproc.getN()).getProfundidad();
			int profAct = ambitoActual().getProfundidad() + 1;
			
			addIns("mst " + (profAct - prof), 5);
			codigoArgumentos(((InsDecProc) insllamadaproc.getN()).getAtributos(), insllamadaproc.getLista());
			addIns("cup " + tam + " " + dir, 0);

			break;
			
		case PROC:
			InsDecProc insdecproc = (InsDecProc) ins;
			maxProfundidad++;
			alturaAmbito = maxProfundidad;
			insdecproc.setDir(codigoP.size());
			
			for (Instruccion p : insdecproc.getAtributos()) {
				
				if (((InsDec) p).getTipo().equals("user")) {
					insdecproc.incrementa(ambitoActual().tam( ((InsDec) p).getTipo().getNombreStruct()));
				} 
				
				else {
					insdecproc.incrementa(1);
				}
			}
			
			addIns("ssp " + ambitoActual().getSsp(), 0);
			int possep2 = codigoP.size();
			addIns("sep ", 0);
			codigoFuncProc(insdecproc.getCuerpo());
			int tampila2 = tamPila(possep2);
			codigoP.get(possep2).setName(codigoP.get(possep2).getName() + tampila2);
			addIns("retp", 0);
			alturaAmbito = ambitoActual().getPadre().getPos();
			
			break;
			
		case SWITCH:
			InsSwitch insswitch = (InsSwitch) ins;
			codigoFuncProc(listaSwitch(insswitch.getVariable(), insswitch.getCases(), 0));
			
			break;
			
		case WHILE:
			InsWhile inswhile = (InsWhile) ins;
			maxProfundidad++;
			alturaAmbito = maxProfundidad;
			
			int ps1 = codigoP.size();
			codigoExpresion(inswhile.getCondicion());
			
			int ps2 = codigoP.size();
			addIns("fjp ", -1);
			codigoFuncProc(inswhile.getCuerpo());
			addIns("ujp " + ps1, 0);
			
			codigoP.get(ps2).setName(codigoP.get(ps2).getName() + codigoP.size());
			alturaAmbito = ambitoActual().getPadre().getPos();
			
			break;
			
		default:
			
			break;
		}
	}

	private void codigoArgumentos(List<Instruccion> ins, List<E> args) {
		for (int i = 0; i < ins.size(); i++) {
			Tipo tipo = ((InsDec)ins.get(i)).getTipo();
			
			if (tipo.equals("user")) {
				codigoExpresionEspecial(args.get(i));
				addIns("movs " + ambitoActual().tam(tipo.getNombreStruct()), ambitoActual().tam(tipo.getNombreStruct()) - 1);
			} 
			
			else {
				codigoExpresion(args.get(i));
			}
		}
	}

	private void codigoFuncProc(List<Instruccion> lista) {
		for (Instruccion i : lista) {
			if (i.tipo() == TipoI.PROC || i.tipo() == TipoI.FUNC) {
				int posUjp = codigoP.size();
				addIns("ujp ", 0);
				codigoInstruccion(i);
				codigoP.get(posUjp).setName(codigoP.get(posUjp).getName() + codigoP.size());
			} 
			
			else {
				codigoInstruccion(i);
			}
		}
	}

	// Asignación de structs o vectores, que tienen varios campos o posiciones
	private void asignaVariosCampos(Iden iden, Tipo tipo, int dir1, int dir2, E valorIni, int prof) {
		
		if (tipo.getNumDim() > 0) {
			
			int aux = 1;
			
			for (int i = 0; i < ambitoActual().tamArray(iden.getId()).size() - 1; i++) {
				aux *= ambitoActual().tamArray(iden.getId()).get(i);
			}
			
			for (int i = 0; i < aux; i++) {
				if (valorIni != null) {
					asignaVariosCampos(iden, tipo, dir1 + i * tamTipo(tipo), dir2, valorIni, prof);
				} 
				
				else {
					asignaVariosCampos(iden, tipo, dir1 + i * tamTipo(tipo),
							dir2 + i * tamTipo(tipo), null, prof);
				}
			}
		} 
		
		else if (tipo.getNombre().equals("user")) {
			
			for (Instruccion insDec : ((InsStruct) tipo.getN()).getCuerpo()) {
				int aux = ambitoActual().dirVar(tipo.getNombreStruct() + "." + 
						((Iden) ((InsDec) insDec).getIden()).getId());
				
				Iden id = new Iden(iden.getId() + "." + ((Iden) ((InsDec) insDec).getIden()).getId(), 0, 0);
				
				if (((InsDec) insDec).getTipo().getNumDim() > 0) {
					ambitoActual().addDim(id.getId(), tamArray((InsDec) insDec));
				}
				
				asignaVariosCampos(id, ((InsDec) insDec).getTipo(), dir1 + aux, dir2 + aux, null, prof);
			}
		} 
		
		else {
			
			addIns("lda " + prof + " " + dir1, 1);
			
			if (dir2 != -1) {
				
				addIns("lda " + prof + " " + dir2, 1);
				addIns("ind", 0);
			} 
			
			else {
				codigoExpresion(valorIni);
			}
			
			addIns("sto", -2);
		}
	}

	private void open(boolean isFunc) {
		Ambito b = new Ambito(isFunc, ambitoActual, ambitosAbiertos.size());
		ambitosAbiertos.add(b);
		ambitoActual = b;
	}

	private void open() {
		Ambito b = new Ambito(ambitoActual, ambitosAbiertos.size());
		ambitosAbiertos.add(b);
		ambitoActual = b;
	}

	private void close() {
		if (!ambitoActual.isFunc()) {
			ambitoActual.getPadre().setSsp(Math.max(ambitoActual.getPadre().getSsp(), maxDir));
		}
		
		maxDir -= ambitoActual.getTamAmbito();
		ambitoActual = ambitoActual.getPadre();
	}

	private void write() throws FileNotFoundException {

		PrintWriter pw;
		
		pw = new PrintWriter(fich);
		for (int i = 0; i < codigoP.size(); ++i) {
			pw.println("{" + i + "}" + codigoP.get(i).getName() + ";");
		}
		
		pw.close();
	}
	
	public void addId(String iden, int tam) {
		ambitoActual.addId(iden, tam);
		maxDir += tam;
		ambitoActual.setSsp(ambitoActual.getSsp() + tam);
	}

	private void addIns(String ins, int tipo) {
		codigoP.add(new InsCodigoP(ins, tipo));
	}

	private Ambito ambitoActual() {
		return ambitosAbiertos.get(alturaAmbito);
	}

	private int tamPila(int ini) {
		int t = 0, max = 0, aux = 0;
		
		for (int i = ini; i < codigoP.size(); i++) {
			
			if (aux == 0) {
				
				if (codigoP.get(i).getName().length() > 2 && codigoP.get(i).getName().substring(0, 3).equals("ssp")) {
					aux++;
				} 
				
				else {
					t += codigoP.get(i).getTipo();
				}
			} 
			
			else {
				if (codigoP.get(i).getName().length() > 3 && (codigoP.get(i).getName().substring(0, 4).equals("retp")
						|| codigoP.get(i).getName().substring(0, 4).equals("retf"))) {
					aux--;
				}
			}
			
			max = Math.max(max, t);
		}
		
		return max;
	}
	
	private List<Integer> tamArray(InsDec ins) {
		List<Integer> lista = new ArrayList<>();
		
		for(E e: ins.getTipo().getLista()) {
			lista.add(Integer.parseInt(((Ent) e).getV()));
		}
		
		if (ins.getTipo().getNombre().equals("user")) {
			lista.add(ambitoActual.tam(ins.getTipo().getNombreStruct()));
		}
		
		else {
			lista.add(1);
		}
		
		return lista;
	}

	private int tamTipo(Tipo tipo) {
		
		if (tipo.getNombre().equals("user")) {
			return ambitoActual().tam(tipo.getNombreStruct());
		} 
		
		else {
			return 1;
		}
	}

	private String stringAccesoStruct(E exp) {
		
		if (exp.tipo() == TipoE.PUNTO) {
			return stringAccesoStruct(exp.opnd1()) + "." + ((Iden) exp.opnd2()).getId();
		} 
		
		else {
			return ((Iden) exp).getId();
		}
	}
	
	private List<Instruccion> listaSwitch(E e, List<Case> lista, int i) {
		
		if(i == lista.size()-1) {
			return lista.get(i).getCuerpo();
		}
		
		else {
			InsIf insif = new InsIf(new Igig(e, lista.get(i).getValor(), 0, 0), lista.get(i).getCuerpo(), 
					listaSwitch(e, lista, i+1), 0, 0);
			
			List<Instruccion> listaIns = new ArrayList<Instruccion>();
			listaIns.add(insif);
			
			return listaIns;
		}
	}
}
