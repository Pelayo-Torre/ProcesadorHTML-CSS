package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Negrita implements AtributosBody{
	
	public List<Texto> textos;
	
	public Negrita(List<Texto> textos) {
		this.textos = textos;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
