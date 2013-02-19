package driver;

import parser.types.ParserStrategy;

public class Compiler
{
	
	private ParserStrategy parser;
	
	public void setParserStrategy(ParserStrategy p)
	{
		
		parser = p;
		
	}
	
	public void compile(String source)
	{
		
		parser.parseProgram(source);
		
	}

}
