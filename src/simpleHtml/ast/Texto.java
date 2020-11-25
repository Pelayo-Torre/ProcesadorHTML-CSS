package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Texto implements AtributosBody{

	public String valor;
	
	public Texto(String valor) {
		this.valor = valor;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
