package render;

import java.util.ArrayList;
import java.util.List;

public class FormattedLine {
	
	private String align;
	private List<FormattedText> formattedTexts;
	
	public FormattedLine(String align) {
		this.align = align;
		this.formattedTexts = new ArrayList<FormattedText>();
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}
	
	public void add(FormattedText text) {
		this.formattedTexts.add(text);
	}
	
	public double calcularMetricas() {
		double metrica = 0.0;
		for(FormattedText ft : this.formattedTexts)
			if(ft != null)
				metrica += ft.metric();
		return metrica;
	}

	public List<FormattedText> getFormattedTexts() {
		return formattedTexts;
	}

	public void setFormattedTexts(List<FormattedText> formattedTexts) {
		this.formattedTexts = formattedTexts;
	}
}
