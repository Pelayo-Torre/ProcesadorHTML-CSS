package simpleCss.main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import simpleCss.parser.Lexicon;
import simpleCss.parser.Token;
import simpleCss.parser.TokensId;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FileReader reader = new FileReader("EX1.CSS");
		Lexicon lex = new Lexicon(reader);
		leerTokens(lex);
	}
	
	private static void leerTokens(Lexicon lex) {
		Token token = lex.getToken();
		while(token.getToken() != TokensId.EOF) {
			System.out.println(token);
			token = lex.getToken();
		}
	}

}
