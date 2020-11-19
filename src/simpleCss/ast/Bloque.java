package simpleCss.ast;

import java.util.List;

import simpleCss.visitor.Visitor;

public class Bloque implements AstCss{
	
	public String identificador;
	public List<Regla> reglas;
	
	public Bloque(String identificador, List<Regla> reglas) {
		this.identificador = identificador;
		this.reglas = reglas;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
