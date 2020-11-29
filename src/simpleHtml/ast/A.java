package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class A implements EtiquetasBody, AtributosBody{

	public HrefA href;
	public List<Texto> textos;
	
	public A(HrefA href, List<Texto> textos) {
		this.href = href;
		this.textos = textos;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
