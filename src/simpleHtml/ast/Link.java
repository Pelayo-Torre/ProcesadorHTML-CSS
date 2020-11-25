package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Link implements Ast{
	
	public List<AtributosLink> atributosLink;
	
	public Link(List<AtributosLink> atributosLink) {
		this.atributosLink = atributosLink;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
