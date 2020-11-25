package simpleHtml.parser;

import java.util.List;

import simpleHtml.ast.*;
import java.util.*;

public class Parser {
	
	private Lexicon lex;
	
	public Parser (Lexicon lex) {
		this.lex = lex;
		this.lex.reset();
	}
	
	public Ast parse () {
		if(lex.hayErrores()) {
			System.out.println("------------- ERRORES DETECTADOS EN EL LÉXICO -------------");
			System.out.println("Corrija los errores para ejecutar el análisis sintáctico");
			return null;
		}
		Token tok = lex.getToken();
		if(!tok.getToken().equals(TokensId.HTML))
			errorSintactico("Se esperaba '<html>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		Programa programa = new Programa(parseHead(), parseBody());		
		
		tok = lex.getToken();
		if(!tok.getToken().equals(TokensId.HTMLFIN))
			errorSintactico("Se esperaba '</html>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		tok = lex.getToken();
		if (!tok.getToken().equals(TokensId.EOF)) {
			errorSintactico ("Encontrado "+tok.getLexeme()+". Se esperaba el final del fichero", tok.getLine());
		}
		
		return programa;
	}
	
	private Head parseHead() {	
		Token tok = lex.getToken();
		
		if(!tok.getToken().equals(TokensId.HEAD)) 
			errorSintactico("Se esperaba '<head>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		Title title = parseTitle();
		Link link = parseLink();
		
		tok = lex.getToken();
		
		if(!tok.getToken().equals(TokensId.HEADFIN)) 
			errorSintactico("Se esperaba '</head>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		return new Head(title, link);
	}
	
	private Title parseTitle() {
		Title title;
		Token tok = lex.getToken();
		
		if(!tok.getToken().equals(TokensId.TITLE))
			errorSintactico("Se esperaba '<title>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		tok = lex.getToken();
		List<Texto> textos = new ArrayList<Texto>();
		while(tok.getToken().equals(TokensId.TEXTO)) {
			textos.add(new Texto(tok.getLexeme()));
			tok = lex.getToken();
		}
		
		title = new Title(textos);
		
		if(!tok.getToken().equals(TokensId.TITLEFIN))
			errorSintactico("Se esperaba '</title>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		return title;
	}
	
	private Link parseLink() {
		Link link = null;
		Token tok = lex.getToken();
		
		if(!tok.getToken().equals(TokensId.LINK))
			errorSintactico("Se esperaba '<link' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		tok = lex.getToken();
		List<AtributosLink> atributosLink = new ArrayList<AtributosLink>();
		while((tok.token == TokensId.HREF) || (tok.token == TokensId.REL) || (tok.token == TokensId.TYPE)) {
			AtributosLink atr = obtenerAtributo(tok);
			if(atr != null)
				atributosLink.add(atr);
			tok = lex.getToken();
		}
		link = new Link(atributosLink);
			
		if(!tok.getToken().equals(TokensId.MAYOR))
			errorSintactico("Se esperaba '>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		return link;
	}
	
	private AtributosLink obtenerAtributo(Token token) {
		switch (token.getToken()) {
			case HREF:
				return new Href(getCadena());
			case REL:
				return new Rel(getCadena());
			case TYPE:
				return new Type(getCadena());
			default:
				errorSintactico("Se esperaba 'href o rel o type' y se ha encontrado " + token.getLexeme(), token.getLine());
				return null;
		}
	}
	
	private Cadena getCadena() {
		Token tok = lex.getToken();
		
		if(!tok.getToken().equals(TokensId.IGUAL))
			errorSintactico("Se esperaba '=' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		tok = lex.getToken();
		
		if(!tok.getToken().equals(TokensId.CADENA)) {
			errorSintactico("Se esperaba 'CADENA' y se ha encontrado " + tok.getLexeme(), tok.getLine());
			return null;
		}
		else {
			return new Cadena(tok.getLexeme());
		}
	}
	
	private Body parseBody() {
		Body body = null;
		
		Token tok = lex.getToken();
		
		if(!tok.getToken().equals(TokensId.BODY))
			errorSintactico("Se esperaba '<body>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		tok = lex.getToken();
		List<EtiquetasBody> etiquetasBody = new ArrayList<EtiquetasBody>();
		while((tok.token == TokensId.H1) || (tok.token == TokensId.H2) || (tok.token == TokensId.P)) {
			EtiquetasBody etiqueta = obtenerEtiqueta(tok);
			if(etiqueta != null)
				etiquetasBody.add(etiqueta);
			tok = lex.getToken();
		}
		
		body = new Body(etiquetasBody);
		
		if(!tok.getToken().equals(TokensId.BODYFIN))
			errorSintactico("Se esperaba '</body>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		
		return body;
	}

	private EtiquetasBody obtenerEtiqueta(Token token) {
		switch (token.token) {
			case H1:
				H1 h1 = new H1(obtenerAtributos());
				if(!lex.getTokenActual().getToken().equals(TokensId.H1FIN))
					errorSintactico("Se esperaba '</h1>' y se ha encontrado " + lex.getTokenActual().getLexeme(), lex.getTokenActual().getLine());
				return h1;
			case H2: 
				H2 h2 = new H2(obtenerAtributos());
				if(!lex.getTokenActual().getToken().equals(TokensId.H2FIN))
					errorSintactico("Se esperaba '</h2>' y se ha encontrado " + lex.getTokenActual().getLexeme(), lex.getTokenActual().getLine());
				return h2;
			case P:
				P p = new P(obtenerAtributos());
				if(!lex.getTokenActual().getToken().equals(TokensId.PFIN))
					errorSintactico("Se esperaba '</p>' y se ha encontrado " + lex.getTokenActual().getLexeme(), lex.getTokenActual().getLine());
				return p;
			default:
				errorSintactico("Se esperaba '<h1> o <h2> o <p>' y se ha encontrado " + token.getLexeme(), token.getLine());
				return null;
		}
	}
	
	private List<AtributosBody> obtenerAtributos(){
		Token tok = lex.getToken();
		
		List<AtributosBody> atributos = new ArrayList<AtributosBody>();
		while((tok.token == TokensId.TEXTO) || (tok.token == TokensId.CURSIVA) || (tok.token == TokensId.NEGRITA) || (tok.token == TokensId.UNDERLINE)) {
			AtributosBody atributo = obtenerAtributoBody(tok);
			if(atributo != null)
				atributos.add(atributo);
			tok = lex.getToken();
		}
		
		return atributos;
	}
	
	private AtributosBody obtenerAtributoBody(Token tok) {
		switch (tok.getToken()) {
			case TEXTO:
				return new Texto(tok.lexeme);
			case CURSIVA:
				Cursiva cursiva = new Cursiva(obtenerTextos());
				if(!lex.getTokenActual().getToken().equals(TokensId.CURSIVAFIN))
					errorSintactico("Se esperaba '</i>' y se ha encontrado " + lex.getTokenActual().getLexeme(), lex.getTokenActual().getLine());
				return cursiva;
			case UNDERLINE :
				Underline underline = new Underline(obtenerTextos());
				if(!lex.getTokenActual().getToken().equals(TokensId.UNDERLINEFIN))
					errorSintactico("Se esperaba '</u>' y se ha encontrado " + lex.getTokenActual().getLexeme(), lex.getTokenActual().getLine());
				return underline;
			case NEGRITA:
				Negrita negrita = new Negrita(obtenerTextos());
				if(!lex.getTokenActual().getToken().equals(TokensId.NEGRITAFIN))
					errorSintactico("Se esperaba '</b>' y se ha encontrado " + lex.getTokenActual().getLexeme(), lex.getTokenActual().getLine());
				return negrita;
			default:
				errorSintactico("Se esperaba 'texto o <i> o <b> o <u>' y se ha encontrado " + tok.getLexeme(), tok.getLine());
				return null;
		}
	}
	
	private List<Texto> obtenerTextos() {
		List<Texto> textos = new ArrayList<Texto>();
		Token tok = lex.getToken();
		while(tok.getToken().equals(TokensId.TEXTO)) {
			Texto texto = new Texto(tok.lexeme);
			textos.add(texto);
			tok = lex.getToken();
		}
		return textos;
	}

	//Gestión de Errores Sintáctico
	void errorSintactico (String e, int line) {
		System.out.println("Error Sintáctico : "+e+" en la línea "+line);
	}
}
