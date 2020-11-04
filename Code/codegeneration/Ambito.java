package codegeneration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ambito {
	// Mapa de cada id su dirrecci�n de memoria
	private Map<String, Integer> dirIden = new HashMap<>();
	// Mapa de cada tipo de usuario a cu�nto ocupa
	private Map<String, Integer> tamStruct = new HashMap<>();
	// Mapa de cada array a su tama�o
	private Map<String, List<Integer>> tamArray = new HashMap<>();
	// Llevamos una lista enlazada de �mbitos, cada hijo lleva un puntero a su padre
	private Ambito padre;
	// Para diferenciar los �mbitos de funciones o procedimientos del resto
	private boolean isFunc = false;
	// Tama�o de las variables del �mbito para reutilizar direcciones cuando lo cerramos
	private int tamAmbito = 0;
	// Profundidad de anidamiento (para declarar funciones dentro de funciones)
	private int profundidad = 0;
	// Siguiente direcci�n que podemos asignar (siempre hay 4 reservadas para llamada a func)
	private int nextDir = 5;
	// N�mero de direcciones en memoria local
	private int ssp = 0;	
	// Posici�n en la lista de �mbitos
	private int pos;

	public Ambito(Ambito padre, int pos) {
		super();
		this.padre = padre;
		this.pos = pos;
		
		if (padre != null) {
			profundidad = padre.getProfundidad();
			nextDir = padre.getNextDir();
		}
	}

	public Ambito(boolean isFunc, Ambito padre, int pos) {
		super();
		this.isFunc = isFunc;
		this.padre = padre;
		this.pos = pos;
		this.ssp = 5;
		
		if (padre != null) {
			profundidad = padre.getProfundidad() + 1;
			
			if (!isFunc) {
				nextDir = padre.getNextDir();
				profundidad = padre.getProfundidad();
			}
		}
	}

	public int getTamAmbito() {
		return tamAmbito;
	}

	public Ambito getPadre() {
		return padre;
	}
	
	public int getPos() {
		return pos;
	}

	public int getSsp() {
		return ssp;
	}

	public void setSsp(int ssp) {
		this.ssp = ssp;
	}

	public boolean isFunc() {
		return isFunc;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public int getNextDir() {
		return nextDir;
	}

	public void setNextDir(int nextDir) {
		this.nextDir = nextDir;
	}

	public void addId(String id, int tam) {
		dirIden.put(id, nextDir);
		tamAmbito += tam;
		nextDir += tam;
	}

	public void addTipo(String tipo, int tam) {
		tamStruct.put(tipo, tam);
	}

	public int dirVar(String id) {
		if (dirIden.containsKey(id)) {
			return dirIden.get(id);
		}
		
		return padre.dirVar(id);
	}

	public int tam(String tipo) {
		if (tamStruct.containsKey(tipo)) {
			return tamStruct.get(tipo);
		}
		
		return padre.tam(tipo);
	}
	
	public void addCampo(String id, int dir) {
		dirIden.put(id, dir);
	}
	
	public void addDim(String id, List<Integer> dim) {
		tamArray.put(id, dim);
	}
	
	public List<Integer> tamArray(String id){
		if(tamArray.containsKey(id)) {
			return tamArray.get(id);
		}
		
		else if(padre!= null) {
			return padre.tamArray(id);
		}
		
		return null;
	}
}
