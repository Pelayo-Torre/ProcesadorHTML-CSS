package simpleHtml.visitor;

import simpleHtml.ast.A;
import simpleHtml.ast.AtributosBody;
import simpleHtml.ast.AtributosLink;
import simpleHtml.ast.Body;
import simpleHtml.ast.Cadena;
import simpleHtml.ast.Cursiva;
import simpleHtml.ast.EtiquetasBody;
import simpleHtml.ast.H1;
import simpleHtml.ast.H2;
import simpleHtml.ast.Head;
import simpleHtml.ast.Href;
import simpleHtml.ast.HrefA;
import simpleHtml.ast.Link;
import simpleHtml.ast.Negrita;
import simpleHtml.ast.P;
import simpleHtml.ast.Programa;
import simpleHtml.ast.Rel;
import simpleHtml.ast.Texto;
import simpleHtml.ast.Title;
import simpleHtml.ast.Type;
import simpleHtml.ast.Underline;

public class PrintAstVisitor implements Visitor{
	
	String sp="   ";

	@Override
	public Object visit(Programa p, Object param) {
		String s, sh, sb;
		sh = (String) p.head.accept(this,sp);
		sb = (String) p.body.accept(this,sp);
		s = "(Program\n"+sh+"\n"+sb+"\n)";
		return s;
	}

	@Override
	public Object visit(Head h, Object param) {
		String s, st, sl;
		st = (String) h.title.accept(this,(String)param+sp);
		sl = (String) h.link.accept(this,(String)param+sp);
		s = sp+"(Head\n"+st+"\n"+sl+"\n"+sp+")";
		return s;
	}

	@Override
	public Object visit(Title t, Object param) {
		String s = (String)param+"(title -> ";
		for(Texto texto : t.textos) {
			s += texto.accept(this, (String)param+sp);
		}
		s = s + (String) param + ")";
		return s;
	}

	@Override
	public Object visit(Link l, Object param) {
		String txt= (String) param + "(link\n";
		for(AtributosLink atributo : l.atributosLink)
			txt += (String) atributo.accept(this,(String)param+sp)+"\n";
		return txt;
	}

	@Override
	public Object visit(Body b, Object param) {
		String s = (String) param +"(body\n";
		for (EtiquetasBody p : b.etiquetas)
			s = s + (String) p.accept(this,(String)param+sp)+"\n";
		s = s + (String) param + ")";
		return s;
	}

	@Override
	public Object visit(H1 h1, Object param) {
		String s= (String) param + "(header 1\n";
		for (AtributosBody h : h1.atributosBody)
			s = s + (String) h.accept(this,(String)param+sp)+"\n";
		s = s + (String) param + ")";
		return s;
	}

	@Override
	public Object visit(H2 h2, Object param) {
		String s= (String) param + "(header 2\n";
		for (AtributosBody h : h2.atributosBody)
			s = s + (String) h.accept(this,(String)param+sp)+"\n";
		s = s + (String) param + ")";
		return s;
	}

	@Override
	public Object visit(P p, Object param) {
		String s= (String) param + "(paragraph\n";
		for (AtributosBody h : p.atributosBody)
			s = s + (String) h.accept(this,(String)param+sp)+"\n";
		s = s + (String) param + ")";
		return s;
	}

	@Override
	public Object visit(Texto tx, Object param) {
		String s= (String) param + "(TEXT -> ";
		s = s + tx.valor + ")";
		return s;
	}

	@Override
	public Object visit(Negrita bt, Object param) {
		String s= (String) param + "(bold\n";
		
		for(Texto texto : bt.textos) {
			s += texto.accept(this, (String)param+sp);
		}
		s = s + "\n" + (String) param + ")";
		return s;
	}

	@Override
	public Object visit(Cursiva it, Object param) {
		String s= (String) param + "(italic\n";
		for(Texto texto : it.textos) {
			s += texto.accept(this, (String)param+sp);
		}
		s = s + "\n" + (String) param + ")";
		return s;
	}

	@Override
	public Object visit(Underline ut, Object param) {
		String s= (String) param + "(underlined\n";
		for(Texto texto : ut.textos) {
			s += texto.accept(this, (String)param+sp);
		}
		s = s + "\n" + (String) param + ")";
		return s;
	}

	@Override
	public Object visit(Href p, Object param) {
		String s = (String) param + " -> HREF";
		s = s + p.cadena.accept(this, (String)param+sp) + "\n";
		return s;
	}

	@Override
	public Object visit(Rel p, Object param) {
		String s = (String) param + " -> REL";
		s = s + p.cadena.accept(this, (String)param+sp) + "\n";
		return s;
	}

	@Override
	public Object visit(Type p, Object param) {
		String s = (String) param + " -> TYPE";
		s = s + p.cadena.accept(this, (String)param+sp) + "\n";
		return s;
	}

	@Override
	public Object visit(Cadena p, Object param) {
		String s= (String) param + "(CADENA -> ";
		s = s + p.valor + ")";
		return s;
	}


	@Override
	public Object visit(HrefA p, Object param) {
		String s = (String) param + " -> HREF";
		s = s + p.cadena.accept(this, (String)param+sp) + "\n";
		return s;
	}

	@Override
	public Object visit(A p, Object param) {
		String s= (String) param + "(a\n";
		s += (String) p.href.accept(this,(String)param+sp)+"\n";
		for(Texto t : p.textos) {
			s += t.accept(this, (String)param+sp);
		}
		s = s + "\n" + (String) param + ")";
		return s;
	}

}
