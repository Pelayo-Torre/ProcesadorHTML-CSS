package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class P implements EtiquetasBody{

	public List<AtributosBody> atributosBody;
	
	public P(List<AtributosBody> atributosBody) {
		this.atributosBody = atributosBody;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
