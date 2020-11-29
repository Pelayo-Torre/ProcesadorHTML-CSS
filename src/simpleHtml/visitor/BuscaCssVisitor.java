package simpleHtml.visitor;

import simpleHtml.ast.A;
import simpleHtml.ast.AtributosLink;
import simpleHtml.ast.Body;
import simpleHtml.ast.Cadena;
import simpleHtml.ast.Cursiva;
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

public class BuscaCssVisitor implements Visitor{

	@Override
	public Object visit(Programa p, Object param) {
		return p.head.accept(this, null);
	}

	@Override
	public Object visit(Body p, Object param) {
		return null;
	}

	@Override
	public Object visit(Head p, Object param) {
		return p.link.accept(this, null);
	}

	@Override
	public Object visit(Title p, Object param) {
		return null;
	}

	@Override
	public Object visit(Texto p, Object param) {
		return null;
	}

	@Override
	public Object visit(Link p, Object param) {
		for(AtributosLink atributo : p.atributosLink)
			if(atributo instanceof Href)
				return ((Href) atributo).cadena.accept(this, null);
		return null;
	}

	@Override
	public Object visit(Href p, Object param) {
		return null;
	}

	@Override
	public Object visit(Rel p, Object param) {
		return null;
	}

	@Override
	public Object visit(Type p, Object param) {
		return null;
	}

	@Override
	public Object visit(H1 p, Object param) {
		return null;
	}

	@Override
	public Object visit(H2 p, Object param) {
		return null;
	}

	@Override
	public Object visit(P p, Object param) {
		return null;
	}

	@Override
	public Object visit(Negrita p, Object param) {
		return null;
	}

	@Override
	public Object visit(Underline p, Object param) {
		return null;
	}

	@Override
	public Object visit(Cursiva p, Object param) {
		return null;
	}

	@Override
	public Object visit(Cadena p, Object param) {
		return p.valor;
	}

	@Override
	public Object visit(HrefA p, Object param) {
		return null;
	}

	@Override
	public Object visit(A p, Object param) {
		return null;
	}

}
