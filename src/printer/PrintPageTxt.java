package printer;

import java.util.Map;

import render.FormattedLine;
import render.FormattedPage;
import render.FormattedText;

public class PrintPageTxt implements IPrintPage{

	@Override
	public void printPage(FormattedPage fp) {
		System.out.println("Página título: " + fp.getTitle());
		for (FormattedLine fl : fp.getFormattedLines())
			printLine(fl);
	}
	
	private void printLine(FormattedLine fl) {
		System.out.println("(Line align : " + fl.getAlign() + " | Metrics : " + fl.calcularMetricas() + " >>");
		for(FormattedText ft : fl.getFormattedTexts())
			printText(ft);
		System.out.println(")");
		System.out.println();
	}
	
	private void printText(FormattedText ft) {
		if(ft.getPropiedades().isEmpty()) {
			System.out.println("(Format : " + ft.getColor() + ", " + ft.getTamano() + 
					", " + ft.getEstilo() + " | Metrics : " + ft.metric() + " >> " + ft.getTexto() + ")");
		}
		else {
			System.out.println("(Format : " + ft.getColor() + ", " + ft.getTamano() + 
					", " + ft.getEstilo() + " | Metrics : " + ft.metric() + " >> " + ft.getTexto() + " | Propiedades: ");
			for (Map.Entry<String, String> entry : ft.getPropiedades().entrySet()) {
			    System.out.print("\t" + entry.getKey() + " = " + entry.getValue() + "");
			}
			System.out.print(")\n");
		}
	}

}
