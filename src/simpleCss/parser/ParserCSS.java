package simpleCss.parser;

import java.util.List;

import simpleCss.ast.*;


import java.util.*;

public class ParserCSS {
	
	private LexiconCSS lex;

	public ParserCSS (LexiconCSS lex) {
		this.lex = lex;
		this.lex.reset();
	}
	
	public Programa parse() {
		if(this.lex.hayErrores()) {
			System.out.println("------------- ERRORES DETECTADOS EN EL L�XICO -------------");
			System.out.println("Corrija los errores para ejecutar el an�lisis sint�ctico");
			return null;
		}
		return new Programa(parseBloque());
	}
	
	private List<Bloque> parseBloque() {
		List<Bloque> bloques = new ArrayList<Bloque>();
		Token tok = lex.getToken();
		while ((tok.token == TokensId.H1) || (tok.token == TokensId.H2) || (tok.token == TokensId.P) || (tok.token == TokensId.A)) {
			Token ident = tok;
			tok = lex.getToken();
			
			if(tok.getToken().equals(TokensId.LLAVE_APERTURA)) {
				Bloque bloque = new Bloque(ident.getLexeme(), parseReglas());
				bloques.add(bloque);
				tok = lex.getToken();
			}
			else {
				errorSintactico("Se esperaba '{' y se ha encontrado " + tok.getLexeme(), tok.getLine());
			}
		}
		
		tok = lex.getToken();
		if (!tok.getToken().equals(TokensId.EOF)) {
			errorSintactico ("Encontrado "+tok.getLexeme()+". Se esperaba el final del fichero", tok.getLine());
		}
		
		return bloques;
	}
	
	private List<Regla> parseReglas(){
		List<Regla> reglas = new ArrayList<Regla>();
		Token tok = lex.getToken();
		while(tok.getToken().equals(TokensId.COLOR) || tok.getToken().equals(TokensId.FONT_SIZE) || 
				tok.getToken().equals(TokensId.FONT_STYLE) || tok.getToken().equals(TokensId.TEXT_ALIGN)) {
			Token clave = tok;
			tok = lex.getToken();
			
			if(!tok.getToken().equals(TokensId.DOS_PUNTOS)) {
				errorSintactico("Se esperaba ':' y se ha encontrado " + tok.getLexeme(), tok.getLine());
			}
			
			tok = lex.getToken();			
			Regla regla = new Regla(clave.getLexeme(), getValor(clave, tok));
			reglas.add(regla);
			
			tok = lex.getToken();
			if(!tok.getToken().equals(TokensId.PUNTO_COMA)) {
				errorSintactico("Se esperaba ';' y se ha encontrado " + tok.getLexeme(), tok.getLine());
			}
			tok = lex.getToken();
		}
		
		if(!tok.getToken().equals(TokensId.LLAVE_CIERRE)) {
			errorSintactico("Se esperaba '}' y se ha encontrado " + tok.getLexeme(), tok.getLine());
		}
		
		return reglas;
	}
	
	private String getColor(Token color) {
		switch(color.getToken()) {
			case BLACK:
				return "black";
			case BLUE:
				return "blue";
			case GREEN:
				return "green";
			case YELLOW:
				return "yellow";
			case RED:
				return "red";
			case WHITE:
				return "white";
		default:
			errorSintactico("Se esperaba 'black / blue / green / yellow / red / white' y se ha encontrado " + color.getLexeme(), color.getLine());
			return null;
		}
	}
	
	private String getFontStyle(Token style) {
		switch(style.getToken()) {
			case ITALIC:
				return "italic";
			case NORMAL:
				return "normal";
			case UNDERLINE:
				return "underline";
			case BOLD:
				return "bold";
		default:
			errorSintactico("Se esperaba 'italic / normal / underline / bold' y se ha encontrado " + style.getLexeme(), style.getLine());
			return null;
		}
	}
	
	private String getTextAlign(Token align) {
		switch(align.getToken()) {
			case LEFT:
				return "left";
			case RIGHT:
				return "right";
			case CENTER:
				return "center";
		default:
			errorSintactico("Se esperaba 'left / right / center' y se ha encontrado " + align.getLexeme(), align.getLine());
			return null;
		}
	}
	
	private String getFontSize(Token size) {
		if(size.getToken().equals(TokensId.SIZE)) {
			return size.getLexeme();
		}
		return null;
	}

	private String getValor(Token clave, Token valor) {
		switch(clave.getToken()) {
			case COLOR:
				return getColor(valor);
			case FONT_SIZE:
				return getFontSize(valor);
			case FONT_STYLE:
				return getFontStyle(valor);
			case TEXT_ALIGN:
				return getTextAlign(valor);
		default:
			return null;
		}
	}
		
	//Gesti�n de Errores Sint�ctico
	private void errorSintactico (String e, int line) {
		System.out.println("Error Sint�ctico : "+e+" en la l�nea "+line);
	}
}
