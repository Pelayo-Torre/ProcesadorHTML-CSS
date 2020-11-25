package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Body implements Ast{
	
	public List<EtiquetasBody> etiquetas;
	
	public Body(List<EtiquetasBody> etiquetas) {
		this.etiquetas = etiquetas;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
