package parser.triv;

import lexer.triv.TrivLexer;
import lexer.triv.TrivRegexLexer;
import lexer.types.Symbol;
import parser.types.AbstractParser;

public class TrivParser extends AbstractParser
{
	
	public TrivParser(String source)
	{
		
		super();
		//lex = new TrivLexer(source);
		lex = new TrivRegexLexer(source);
		
	}

	@Override
	protected Symbol expression()
	{
		
		Symbol sym = lex.getCurrentSymbol();
		System.out.println(sym);
		
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

	@Override
	public void parseProgram()
	{
		
		lex.initialise();
		expression();
		System.out.println(symbolTable);
		System.out.println(codeVector);
		
	}

}