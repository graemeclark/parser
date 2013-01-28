package parser.types;

import lexer.types.AbstractLexer;

public abstract class AbstractParser
{
	
	protected AbstractLexer lex;
	protected SymbolTable symbolTable;
	
	public AbstractParser()
	{
		
		symbolTable = new SymbolTable();
		
	}
	
	public abstract void expression();
	
	public abstract void parseProgram();

}
