package asem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.NodoArbol;

public class TablaDeSimbolos {
	private List<Map<String, NodoArbol>> tabla = new ArrayList<>();
	
	public void open() {
		tabla.add(new HashMap<>());
	}
	
	public void close() {
		tabla.remove(tabla.size() - 1);
	}
	
	public boolean addId(String id, NodoArbol instr) {
		
		int aux = tabla.size() - 1;
		boolean ok = !tabla.get(aux).containsKey(id);
		
		if(ok) {
			tabla.get(aux).put(id, instr);
		}
		
		else {
			System.err.println("*** WARNING fila " + instr.getFila()+ ", columna "+ instr.getCol() + 
					" variable "+ id + " ya declarada, no es posible volver a declararla");
		}
		
		return ok;
	}
	
	public NodoArbol getDec(String iden) {
		
		int aux = tabla.size() - 1;
		for(int i = aux; i > - 1; i--) {
			if(tabla.get(i).containsKey(iden)) {
				return tabla.get(i).get(iden);
			}
		}
		
		return null;
	}
}
