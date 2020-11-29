package main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import printer.IPrintPage;
import printer.PrintPageTxt;
import render.FormattedPage;
import render.Render;
import simpleCss.ast.AstCss;
import simpleCss.visitor.BuscaParamCssVisitor;
import simpleHtml.ast.Ast;
import simpleHtml.parser.LexiconHTML;
import simpleHtml.parser.ParserHTML;
import simpleHtml.visitor.BuscaCssVisitor;

public class Main {

	public static void main(String[] args)  {
		
		try  {
			//Ast HTML
			
			System.out.println("1. Generando AST del fichero HTML");
			
			FileReader reader = new FileReader("EX4.html");
			LexiconHTML lex = new LexiconHTML(reader);
			ParserHTML parser = new ParserHTML(lex);
			Ast arbolHtml = parser.parse();
			
			if(arbolHtml != null) {
				System.out.println("2. AST del fichero HTML generado");
				
				//AST NOMBRE CSS EN HTML
				
				System.out.println("3. Búsqueda del fichero CSS");
				
				BuscaCssVisitor buscaCss = new BuscaCssVisitor();
				String css = (String) arbolHtml.accept(buscaCss, null);
				
				System.out.println("4. fichero CSS encontrado: " + css);
				
				//AST CSS
				
				System.out.println("5. Generando AST del fichero CSS");
				
				FileReader readerCss;
				if(css == null) {
					System.out.println("      - No se ha especificado CSS en el HTML, se cogerá el css por defecto.");
					readerCss = new FileReader("Default.CSS");
				}
				else {
					readerCss = new FileReader(css);
				}
				simpleCss.parser.LexiconCSS lexCss = new simpleCss.parser.LexiconCSS(readerCss);
				simpleCss.parser.ParserCSS parserCss = new simpleCss.parser.ParserCSS(lexCss);
				AstCss arbolCss = parserCss.parse();
				
				if(arbolCss != null) {
					System.out.println("6. AST del fichero CSS generado");
					
					//AST CSS Fichero por defecto
					
					System.out.println("7. Generando AST del fichero CSS por defecto");
					
					readerCss = new FileReader("Default.CSS");
					lexCss = new simpleCss.parser.LexiconCSS(readerCss);
					parserCss = new simpleCss.parser.ParserCSS(lexCss);
					AstCss arbolCssDefault = parserCss.parse();
					
					System.out.println("8. AST del fichero CSS generado");
					
					//RENDERIZACIÓN DE PÁGINA
					
					Render render = new Render(new BuscaParamCssVisitor(), arbolCssDefault, arbolCss);
					System.out.println();
					System.out.println("RENDERIZADO \n");
					IPrintPage print = new PrintPageTxt();
					print.printPage((FormattedPage) arbolHtml.accept(render, null));
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Fichero no encontrado. Se adjunta traza del error: ");
			System.out.println(e.toString());
		}
		
	}

}
