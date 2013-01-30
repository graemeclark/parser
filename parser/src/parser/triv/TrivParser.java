package parser.triv;

import lexer.triv.TrivLexer;
import lexer.types.Symbol;
import parser.types.AbstractParser;

public class TrivParser extends AbstractParser
{
	
	public TrivParser(String source)
	{
		
		super();
		lex = new TrivLexer(source);
		
	}

	@Override
	public Symbol expression()
	{
		
		Symbol sym = lex.currentSymbol;
		
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
		
		lex.mustBe("identifier");
		Symbol variable = lex.currentSymbol;
		lex.nextSymbol();
		
		lex.mustBe("=");
		lex.nextSymbol();
		Symbol init = expression();
		symbolTable.put(variable.getValue(), init.getValue());

		lex.mustBe("in");
		
		lex.nextSymbol();
		expression();
		
	}

	@Override
	public void parseProgram()
	{
		
		lex.nextSymbol();
		expression();
		System.out.println(symbolTable);
		System.out.println(codeVector);
		
	}

}