package parser.types;

import java.util.ArrayList;
import java.util.List;

//import lexer.types.AbstractLexer;
import lexer.types.AbstractRegexLexer;
import lexer.types.Symbol;

public abstract class AbstractParser
{
	
	//protected AbstractLexer lex;
	protected AbstractRegexLexer lex;
	protected SymbolTable symbolTable;
	protected List<String> codeVector;
	
	public AbstractParser()
	{
		
		symbolTable = new SymbolTable();
		codeVector = new ArrayList<String>();
		
	}
	
	protected abstract Symbol expression();
	
	public abstract void parseProgram();

}
