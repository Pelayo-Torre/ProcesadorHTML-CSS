package render;

import paint.*;

public class Main {

	public static void main(String[] args) {
		FormattedPage formattedPage = createPage();
		IPrintPage printer = new PrintPageTxt();
		printer.printPage(formattedPage);
	}

	static FormattedPage createPage  () {
		FormattedPage formattedPage= new FormattedPage();
		formattedPage.setTitle("Ejemplo de estilos CSS en el propio documento");
		FormattedLine line1 = createLine1();
		formattedPage.add(line1);
		FormattedLine line2 = createLine2();
		formattedPage.add(line2);
		FormattedLine line3 = createLine3();
		formattedPage.add(line3);
		FormattedLine line4 = createLine4();
		formattedPage.add(line4);
		return formattedPage;
	}
	
	static FormattedLine createLine1 () {
		FormattedLine fl = new FormattedLine ("center");
		FormattedText ft = new FormattedText ("Titulo", "black", 32, "normal");
		fl.add(ft);
		return fl;
	}

	static FormattedLine createLine2 () {
		FormattedLine fl = new FormattedLine ("left");
		FormattedText ft = new FormattedText ("Ejemplo 1", "blue", 18, "italic");
		fl.add(ft);
		return fl;
	}

	static FormattedLine createLine3 () {
		FormattedLine fl = new FormattedLine ("left");
		FormattedText ft = new FormattedText ("Un parrafo de texto.", "green", 12, "normal");
		fl.add(ft);
		return fl;
	}

	static FormattedLine createLine4 () {
		FormattedLine fl = new FormattedLine ("left");
		FormattedText ft = new FormattedText ("Esto ", "green", 12, "normal");
		fl.add(ft);
		ft = new FormattedText ("prueba  ", "green", 12, "bold");
		fl.add(ft);
		ft = new FormattedText ("las ", "green", 12, "normal");
		fl.add(ft);
		ft = new FormattedText ("etiquetas ", "green", 12, "italic");
		fl.add(ft);
		ft = new FormattedText ("de ", "green", 12, "normal");
		fl.add(ft);
		ft = new FormattedText ("formato ", "green", 12, "underlined");
		fl.add(ft);
		ft = new FormattedText ("html ", "green", 12, "normal");
		fl.add(ft);
		return fl;
	}

}
