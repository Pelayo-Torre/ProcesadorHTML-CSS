package render;

import java.util.HashMap;
import java.util.Map;

public class FormattedText {
	
	private String texto;
	private String color;
	private double tamano;
	private String estilo;
	private Map<String, String> propiedades = new HashMap<String, String>();
	
	public FormattedText(String texto, String color, double tamano, String estilo) {
		this.texto = texto;
		this.color = color;
		this.tamano = tamano;
		this.estilo = estilo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getTamano() {
		return tamano;
	}

	public void setTamano(double tamano) {
		this.tamano = tamano;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
	public double metric() {
		if(this.texto != null)
			return this.tamano * this.texto.trim().length();
		return 0.0;
	}

	public Map<String, String> getPropiedades() {
		return propiedades;
	}

}
