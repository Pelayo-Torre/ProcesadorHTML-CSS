package simpleHtmlMain;

import java.io.FileNotFoundException;
import java.io.FileReader;

import simpleHtml.ast.Ast;
import simpleHtml.parser.Lexicon;
import simpleHtml.parser.Parser;
import simpleHtml.parser.Token;
import simpleHtml.parser.TokensId;
import simpleHtml.visitor.BuscaCssVisitor;
import simpleHtml.visitor.PrintAstVisitor;


public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		FileReader reader = new FileReader("EX4.html");
		System.out.println("--------------- INICIO LÉXICO ---------------");
		Lexicon lex = new Lexicon(reader);
		leerTokens(lex);
		System.out.println("--------------- FINAL LÉXICO ---------------");
		System.out.println("--------------- INICIO SINTÁCTICO ---------------");
		Parser parser = new Parser(lex);
		Ast arbolAsst = parser.parse();
		System.out.println("--------------- FINAL SINTÁCTICO ---------------");
		System.out.println();
		System.out.println("--------------- GENERACIÓN DEL ÁRBOL AST ---------------");
		if(arbolAsst != null) {
			PrintAstVisitor printVisitor = new PrintAstVisitor();
			String arbol = (String) arbolAsst.accept(printVisitor, "");
			System.out.println(arbol);
			System.out.println();
			System.out.println("--------------- BÚSQUEDA DE CSS ---------------");
			BuscaCssVisitor buscaCss = new BuscaCssVisitor();
			String css = (String) arbolAsst.accept(buscaCss, null);
			System.out.println("EL CSS encontrado es : " + css);
		}
	}
	
	
	private static void leerTokens(Lexicon lex) {
		Token token = lex.getToken();
		while(token.getToken() != TokensId.EOF) {
			System.out.println(token);
			token = lex.getToken();
		}
	}

}
