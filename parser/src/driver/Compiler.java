package driver;

import lexer.types.LexerStrategy;
import lexer.types.PatternStrategy;
import parser.types.ParserStrategy;

public class Compiler
{
	
	private ParserStrategy parser;
	private LexerStrategy lex;
	
	public void setParserStrategy(ParserStrategy p)
	{
		
		parser = p;
		
	}
	
	public void setLexerStrategy(LexerStrategy l)
	{
		
		lex = l;
		
	}
	
	public void setPatternStrategy(PatternStrategy p)
	{
		
		lex.setPatternStrategy(p);
		
	}
	
	public void compile(String source)
	{
		
		parser.parse(lex, source);
		
	}

}
