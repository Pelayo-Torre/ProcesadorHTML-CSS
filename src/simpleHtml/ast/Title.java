package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Title implements Ast{
	
	public List<Texto> textos;
	
	public Title(List<Texto> textos) {
		this.textos = textos;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
