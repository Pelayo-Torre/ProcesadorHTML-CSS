package render;

import java.util.ArrayList;
import java.util.List;

public class FormattedPage {
	
	private String title;
	private List<FormattedLine> formattedLines;
	
	public FormattedPage() {
		this.formattedLines = new ArrayList<FormattedLine>();
	}
	
	public void add(FormattedLine fl) {
		this.formattedLines.add(fl);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FormattedLine> getFormattedLines() {
		return formattedLines;
	}

	public void setFormattedLines(List<FormattedLine> formattedLines) {
		this.formattedLines = formattedLines;
	}
	
	
}
