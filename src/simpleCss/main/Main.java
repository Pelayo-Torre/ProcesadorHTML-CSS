package simpleCss.main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import simpleCss.ast.AstCss;
import simpleCss.parser.LexiconCSS;
import simpleCss.parser.ParserCSS;
import simpleCss.parser.Token;
import simpleCss.parser.TokensId;
import simpleCss.visitor.BuscaParamCssVisitor;
import simpleCss.visitor.PrintCssAstVisitor;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FileReader reader = new FileReader("EX1.CSS");
		System.out.println("--------------- INICIO LÉXICO ---------------");
		LexiconCSS lex = new LexiconCSS(reader);
		leerTokens(lex);
		System.out.println("--------------- FINAL LÉXICO ---------------");
		System.out.println();
		System.out.println("--------------- INICIO SINTÁCTICO ---------------");
		ParserCSS parser = new ParserCSS(lex);
		AstCss arbolAsst = parser.parse();
		System.out.println("--------------- FINAL SINTÁCTICO ---------------");
		System.out.println();
		System.out.println("--------------- GENERACIÓN DEL ÁRBOL AST ---------------");
		if(arbolAsst != null) {
			PrintCssAstVisitor printVisitor = new PrintCssAstVisitor();
			String arbol = (String) arbolAsst.accept(printVisitor, null);
			System.out.println(arbol);
			System.out.println();
			System.out.println("--------------- BÚSQUEDA DE PARÁMETROS ---------------");
			BuscaParamCssVisitor buscaParam = new BuscaParamCssVisitor();
			System.out.println(buscaParam.buscar("h1", "color", arbolAsst));
		}
		
	}
	
	private static void leerTokens(LexiconCSS lex) {
		Token token = lex.getToken();
		while(token.getToken() != TokensId.EOF) {
			System.out.println(token);
			token = lex.getToken();
		}
	}

}
