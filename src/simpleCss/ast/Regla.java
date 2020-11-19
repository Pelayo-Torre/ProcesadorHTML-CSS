package simpleCss.ast;

import simpleCss.visitor.Visitor;

public class Regla implements AstCss{
	
	public String clave;
	public String valor;
	
	public Regla(String clave, String valor) {
		this.clave = clave;
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
	
	

}
