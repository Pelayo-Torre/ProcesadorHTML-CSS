package render;

import java.util.ArrayList;
import java.util.List;

import simpleCss.ast.AstCss;
import simpleCss.visitor.BuscaParamCssVisitor;
import simpleHtml.ast.A;
import simpleHtml.ast.AtributosBody;
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
import simpleHtml.visitor.Visitor;

public class Render implements Visitor{
	
	private BuscaParamCssVisitor buscaParamCss;
	private AstCss defaultCss;
	private AstCss css;
	
	public Render(BuscaParamCssVisitor buscaParamCss, AstCss defaultCss, AstCss css) {
		this.buscaParamCss = buscaParamCss;
		this.defaultCss = defaultCss;
		this.css = css;
	}

	@Override
	public Object visit(Programa p, Object param) {
		FormattedPage formattedPage = (FormattedPage) p.body.accept(this, null);
		String title = (String) p.head.accept(this, null);
		formattedPage.setTitle(title);
		return formattedPage;
	}

	@Override
	public Object visit(Body p, Object param) {
		FormattedPage formattedPage = new FormattedPage();
		for(EtiquetasBody etiqueta : p.etiquetas)
			formattedPage.add((FormattedLine) etiqueta.accept(this, "E"));
		return formattedPage;
	}

	@Override
	public Object visit(Head p, Object param) {
		return (String) p.title.accept(this, null);
	}

	@Override
	public Object visit(Title p, Object param) {
		String title = "";
		for(Texto texto: p.textos)
			title += (String) texto.accept(this, null) + " ";
		return title.substring(0, title.length()-1);
	}

	@Override
	public Object visit(Texto p, Object param) {
		return p.valor;
	}

	@Override
	public Object visit(Link p, Object param) {
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
		String align = buscaParamCss.buscar("h1", "text-align", css);
		if(align == null)
			align = buscaParamCss.buscar("h1", "text-align", defaultCss);
		FormattedLine formattedLine = new FormattedLine(align);
		List<Texto> textos = new ArrayList<Texto>();
		for(AtributosBody atr : p.atributosBody) {
			if(atr instanceof Texto) 
				textos.add((Texto) atr);
			else {
				if(textos.size() != 0) 
					formattedLine.add(generateFormattedText("h1", null, textos));
				formattedLine.add((FormattedText) atr.accept(this, "h1"));
				textos = new ArrayList<Texto>();
			}
		}
		if(textos.size() != 0) 
			formattedLine.add(generateFormattedText("h1", null, textos));
		return formattedLine;
	}

	@Override
	public Object visit(H2 p, Object param) {
		String align = buscaParamCss.buscar("h2", "text-align", css);
		if(align == null)
			align = buscaParamCss.buscar("h2", "text-align", defaultCss);
		FormattedLine formattedLine = new FormattedLine(align);
		List<Texto> textos = new ArrayList<Texto>();
		for(AtributosBody atr : p.atributosBody) {
			if(atr instanceof Texto) 
				textos.add((Texto) atr);
			else {
				if(textos.size() != 0)
					formattedLine.add(generateFormattedText("h2", null, textos));
				formattedLine.add((FormattedText) atr.accept(this, "h2"));
			}
		}
		if(textos.size() != 0) 
			formattedLine.add(generateFormattedText("h2", null, textos));
		return formattedLine;
	}

	@Override
	public Object visit(P p, Object param) {
		String align = buscaParamCss.buscar("p", "text-align", css);
		if(align == null)
			align = buscaParamCss.buscar("p", "text-align", defaultCss);
		FormattedLine formattedLine = new FormattedLine(align);
		List<Texto> textos = new ArrayList<Texto>();
		for(AtributosBody atr : p.atributosBody) {
			if(atr instanceof Texto) 
				textos.add((Texto) atr);
			else {
				if(textos.size() != 0)
					formattedLine.add(generateFormattedText("p", null, textos));
				formattedLine.add((FormattedText) atr.accept(this, "p"));
				textos = new ArrayList<Texto>();
			}
		}
		if(textos.size() != 0) 
			formattedLine.add(generateFormattedText("p", null, textos));
		return formattedLine;
	}

	@Override
	public Object visit(Negrita p, Object param) {
		return generateFormattedText(param, "bold", p.textos);
	}
	
	@Override
	public Object visit(Underline p, Object param) {
		return generateFormattedText(param, "underline", p.textos);
	}

	@Override
	public Object visit(Cursiva p, Object param) {
		return generateFormattedText(param, "italic", p.textos);
	}

	@Override
	public Object visit(Cadena p, Object param) {
		return p.valor;
	}
	
	@Override
	public Object visit(A p, Object param) {
		if("E".equals(param)) {
			String align = buscaParamCss.buscar("a", "text-align", css);
			if(align == null)
				align = buscaParamCss.buscar("a", "text-align", defaultCss);
			FormattedLine formattedLine = new FormattedLine(align);
			
			FormattedText ft = generateFormattedText("a", null, p.textos);
			ft.getPropiedades().put("href", (String) p.href.accept(this, null));
			formattedLine.add(ft);
			return formattedLine;
		}
		else {
			FormattedText ft = generateFormattedText("a", null, p.textos);
			ft.getPropiedades().put("href", (String) p.href.accept(this, null));
			return ft;
		}
	}

	@Override
	public Object visit(HrefA p, Object param) {
		return (String) p.cadena.accept(this, param);
	}
	
	private FormattedText generateFormattedText(Object param, String estilo, List<Texto> textos) {
		String s = (String) param;
		String color = buscaParamCss.buscar(s, "color", css);
		if(color == null)
			color = buscaParamCss.buscar(s, "color", defaultCss);
		String size = buscaParamCss.buscar(s, "font-size", css);
		if(size == null)
			size = buscaParamCss.buscar(s, "font-size", defaultCss);
		size = size.substring(0, size.length()-2);
		Double font_size = Double.parseDouble(size);
		if(estilo == null) {
			estilo = buscaParamCss.buscar(s, "font-style", css);
			if(estilo == null)
				estilo = buscaParamCss.buscar(s, "font-style", defaultCss);
		}
		String text = "";
		for(Texto texto : textos) {
			text += (String) texto.accept(this, null) + " ";
		}
		return new FormattedText(text.substring(0, text.length()-1), color, font_size, estilo);
	}

}
