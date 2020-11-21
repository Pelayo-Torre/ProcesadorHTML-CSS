package simpleCss.visitor;

import simpleCss.ast.*;

public class PrintCssAstVisitor implements Visitor {
	String sp = "   ";

	@Override
	public Object visit(Programa p, Object param) {
		String sd = "", sr;
		for (Bloque d : p.bloques) {
			sd = sd + (String) d.accept (this,sp);
		}
		sr = "(CSS bloques\n"+ sd + ")";
		return sr;
	}

	@Override
	public Object visit(Bloque d, Object param) {
		String spar = "", sr;
		for (Regla p : d.reglas) {
			spar = spar + (String) p.accept (this,(String) param+sp);
		}
		sr = (String) param + "(" + d.identificador + "\n" + spar + (String) param + ")\n";
		return sr;
	}

	@Override
	public Object visit(Regla p, Object param) {
		return (String) param + p.clave + " --> " + p.valor + "\n";
	}

}
