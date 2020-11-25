package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Href implements AtributosLink{
	
	public Cadena cadena;
	
	public Href(Cadena cadena) {
		this.cadena = cadena;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
