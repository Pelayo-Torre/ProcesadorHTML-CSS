package simpleHtml.ast;


import simpleHtml.visitor.Visitor;

public class Head implements Ast{
	
	public Title title;
	public Link link;
	
	public Head(Title title, Link link) {
		this.title = title;
		this.link = link;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
