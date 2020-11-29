package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class HrefA implements Ast{
	
	public Cadena cadena;
	
	public HrefA(Cadena cadena) {
		this.cadena = cadena;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
