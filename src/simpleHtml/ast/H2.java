package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class H2 implements EtiquetasBody{

	public List<AtributosBody> atributosBody;
	
	public H2(List<AtributosBody> atributosBody) {
		this.atributosBody = atributosBody;
	}
	
	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
