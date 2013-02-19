package parser.types;

import java.util.ArrayList;
import java.util.List;

import lexer.triv.Lexer;
import lexer.types.AbstractRegexLexer;
import lexer.types.Symbol;

public class TRIVParserStrategy implements ParserStrategy
{
	
	protected AbstractRegexLexer lex;
	protected SymbolTable symbolTable;
	protected List<String> codeVector;
	
	public TRIVParserStrategy()
	{
		
		symbolTable = new SymbolTable();
		codeVector = new ArrayList<String>();
		
	}

	public void parseProgram(String source)
	{
		
		lex = new Lexer(source);
		lex.initialise();
		expression();
		System.out.println(symbolTable);
		System.out.println(codeVector);
		
	}

	public Symbol expression()
	{
		
		Symbol sym = lex.getCurrentSymbol();
		
		if (lex.have("let")) {
			letExpression();
		}
		
		else if (lex.have("identifier")) {
			if (symbolTable.lookup(sym) != null) {
				codeVector.add("stackLoad");
				codeVector.add(sym.getValue());
			}
		}
		
		else if (lex.have("numericLiteral")) {
			codeVector.add("loadInt");
			codeVector.add(sym.getValue());
		}
		
		else if (lex.have("stringLiteral")) {
			codeVector.add("loadString");
			codeVector.add(sym.getValue());
		}
		
		return sym;
		
	}
	
	private void letExpression()
	{
		
		Symbol variable = lex.getCurrentSymbol();
		
		lex.mustBe("identifier");
		lex.mustBe("=");
		
		Symbol init = expression();
		symbolTable.put(variable.getValue(), init.getValue());

		lex.mustBe("in");

		expression();
		
	}

}
