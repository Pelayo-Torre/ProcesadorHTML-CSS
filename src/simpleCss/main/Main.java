package simpleCss.main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import simpleCss.ast.AstCss;
import simpleCss.parser.Lexicon;
import simpleCss.parser.Parser;
import simpleCss.parser.Token;
import simpleCss.parser.TokensId;
import simpleCss.visitor.PrintCssAstVisitor;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FileReader reader = new FileReader("EX1.CSS");
		System.out.println("--------------- INICIO L�XICO ---------------");
		Lexicon lex = new Lexicon(reader);
		leerTokens(lex);
		System.out.println("--------------- FINAL L�XICO ---------------");
		System.out.println();
		System.out.println("--------------- INICIO SINT�CTICO ---------------");
		Parser parser = new Parser(lex);
		AstCss arbolAsst = parser.parse();
		System.out.println("--------------- FINAL SINT�CTICO ---------------");
		System.out.println();
		System.out.println("--------------- GENERACI�N DEL �RBOL AST ---------------");
		if(arbolAsst != null) {
			PrintCssAstVisitor printVisitor = new PrintCssAstVisitor();
			String arbol = (String) arbolAsst.accept(printVisitor, null);
			System.out.println(arbol);
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
