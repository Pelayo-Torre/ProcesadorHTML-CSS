package main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import paint.IPrintPage;
import paint.PrintPageTxt;
import render.FormattedPage;
import simpleCss.ast.AstCss;
import simpleCss.visitor.BuscaParamCssVisitor;
import simpleHtml.ast.Ast;
import simpleHtml.ast.Programa;
import simpleHtml.parser.Lexicon;
import simpleHtml.parser.Parser;
import simpleHtml.visitor.BuscaCssVisitor;
import simpleHtml.visitor.RenderVisitor;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		//Ast HTML
		
		System.out.println("1. Generando AST del fichero HTML");
		
		FileReader reader = new FileReader("EX4.html");
		Lexicon lex = new Lexicon(reader);
		Parser parser = new Parser(lex);
		Ast arbolHtml = parser.parse();
		
		System.out.println("2. AST del fichero HTML generado");
		
		//AST NOMBRE CSS EN HTML
		
		System.out.println("3. Búsqueda del fichero CSS");
		
		BuscaCssVisitor buscaCss = new BuscaCssVisitor();
		String css = (String) arbolHtml.accept(buscaCss, null);
		
		System.out.println("4. fichero CSS encontrado: " + css);
		
		//AST CSS
		
		System.out.println("5. Generando AST del fichero CSS");
		
		FileReader readerCss = new FileReader(css);
		simpleCss.parser.Lexicon lexCss = new simpleCss.parser.Lexicon(readerCss);
		simpleCss.parser.Parser parserCss = new simpleCss.parser.Parser(lexCss);
		AstCss arbolCss = parserCss.parse();
		
		System.out.println("6. AST del fichero CSS generado");
		
		//AST CSS Fichero por defecto
		
		System.out.println("7. Generando AST del fichero CSS por defecto");
		
		readerCss = new FileReader("Default.CSS");
		lexCss = new simpleCss.parser.Lexicon(readerCss);
		parserCss = new simpleCss.parser.Parser(lexCss);
		AstCss arbolCssDefault = parserCss.parse();
		
		System.out.println("8. AST del fichero CSS generado");
		
		//RENDERIZACIÓN DE PÁGINA
		
		RenderVisitor render = new RenderVisitor(new BuscaParamCssVisitor(), arbolCssDefault, arbolCss);
		System.out.println();
		System.out.println("RENDERIZADO \n");
		IPrintPage print = new PrintPageTxt();
		print.printPage((FormattedPage) arbolHtml.accept(render, null));
		
	}

}
