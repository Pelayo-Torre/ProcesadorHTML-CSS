package simpleCss.ast;

import java.util.ArrayList;
import java.util.List;

import simpleCss.visitor.Visitor;

public class Programa implements AstCss{
	
	public List<Bloque> bloques = new ArrayList<Bloque>();
	
	public Programa(List<Bloque> bloques) {
		this.bloques = bloques;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
