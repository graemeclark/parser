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
		
		Symbol sym = null;
		
		if (lex.have("let")) {
			//System.out.println("let");
			//sym = lex.nextSymbol();
			//System.out.println(sym);
			letExpression();
		}
		
		else if (lex.have("identifier")) {
			System.out.println("identifier");
			sym = lex.nextSymbol();
			if (symbolTable.lookup(sym) != null) {
				codeVector.add("stackLoad");
				codeVector.add(sym.getValue());
			}
		}
		
		else if (lex.have("numericLiteral")) {
			System.out.println("numericLiteral");
			sym = lex.nextSymbol();
			codeVector.add("loadInt");
			codeVector.add(sym.getValue());
		}
		
		//System.out.println(sym);
		return sym;
		
	}
	
	private void letExpression()
	{
		System.out.println("LET");
		lex.mustBe("identifier");
		System.out.println("YES");
		lex.mustBe("=");
		System.out.println("YES");
		Symbol s = expression();
		lex.mustBe("in");
		
	}

	@Override
	public void parseProgram()
	{
		
		expression();
		System.out.println("YES");
		
	}

}