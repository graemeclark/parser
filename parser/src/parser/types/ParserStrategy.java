package parser.types;

import lexer.types.LexerStrategy;
import lexer.types.Symbol;

public interface ParserStrategy
{
	
	public void parse(LexerStrategy l, String source);
	
	public Symbol expression();

}
