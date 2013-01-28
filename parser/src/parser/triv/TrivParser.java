package parser.triv;

import lexer.triv.TrivLexer;
import parser.types.AbstractParser;

public class TrivParser extends AbstractParser
{
	
	public TrivParser(String source)
	{
		
		super();
		lex = new TrivLexer(source);
		
	}

	@Override
	public void expression()
	{
		
	}

	@Override
	public void parseProgram()
	{
		
	}

}