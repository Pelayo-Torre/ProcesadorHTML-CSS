package simpleHtml.visitor;

import simpleHtml.ast.*;

public interface Visitor {
	
	Object visit(Programa p, Object param);
	Object visit(Body p, Object param);
	Object visit(Head p, Object param);
	Object visit(Title p, Object param);
	Object visit(Texto p, Object param);
	Object visit(Link p, Object param);
	Object visit(Href p, Object param);
	Object visit(Rel p, Object param);
	Object visit(Type p, Object param);
	Object visit(H1 p, Object param);
	Object visit(H2 p, Object param);
	Object visit(P p, Object param);
	Object visit(Negrita p, Object param);
	Object visit(Underline p, Object param);
	Object visit(Cursiva p, Object param);
	Object visit(Cadena p, Object param);
}
