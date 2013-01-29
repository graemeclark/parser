package parser.types;

import java.util.ArrayList;
import java.util.List;

import lexer.types.AbstractLexer;
import lexer.types.Symbol;

public abstract class AbstractParser
{
	
	protected AbstractLexer lex;
	protected SymbolTable symbolTable;
	protected List<String> codeVector;
	
	public AbstractParser()
	{
		
		symbolTable = new SymbolTable();
		codeVector = new ArrayList<String>();
		
	}
	
	public abstract Symbol expression();
	
	public abstract void parseProgram();

}
