package simpleCss.visitor;

import simpleCss.ast.AstCss;
import simpleCss.ast.Bloque;
import simpleCss.ast.Programa;
import simpleCss.ast.Regla;

public class BuscaParamCssVisitor implements Visitor{
	
	private String identificador;
	private String etiqueta;

	@Override
	public Object visit(Programa p, Object param) {
		//Se recorren los bloques del fichero css
		for(Bloque bloque : p.bloques) 
			if(bloque.identificador.equals(this.identificador))
				return (String) bloque.accept(this, null);
		return null;
	}

	@Override
	public Object visit(Regla p, Object param) {
		return p.valor;
	}

	@Override
	public Object visit(Bloque p, Object param) {
		//Se recorren las reglas de cada bloque
		for(Regla regla : p.reglas)
			if(regla.clave.equals(this.etiqueta))
				return (String) regla.accept(this, null);
		return null;
	}
	
	public String buscar(String identificador, String etiqueta, AstCss arbol) {
		this.etiqueta = etiqueta;
		this.identificador = identificador;
		
		if(this.etiqueta == null || this.identificador == null)
			return null;
		return (String) arbol.accept(this, null);
	}

}
