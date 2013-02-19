package parser.types;

import lexer.types.Symbol;

public interface ParserStrategy
{
	
	public void parseProgram(String source);
	public Symbol expression();

}
