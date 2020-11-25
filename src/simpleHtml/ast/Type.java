package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Type implements AtributosLink{

	public Cadena cadena;
	
	public Type(Cadena cadena) {
		this.cadena = cadena;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
