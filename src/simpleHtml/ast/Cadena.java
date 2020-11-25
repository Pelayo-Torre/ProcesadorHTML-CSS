package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Cadena implements Ast{
	
	public String valor;
	
	public Cadena(String valor) {
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
