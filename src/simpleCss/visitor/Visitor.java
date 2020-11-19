package simpleCss.visitor;

import simpleCss.ast.*;

public interface Visitor {
	
	Object visit(Programa p, Object param);
	Object visit(Regla p, Object param);
	Object visit(Bloque p, Object param);
	
}
