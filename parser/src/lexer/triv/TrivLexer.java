package lexer.triv;

import lexer.types.AbstractLexer;
import lexer.types.Symbol;


public class TrivLexer extends AbstractLexer
{

	private static String[] res = { "let", "in" };
	
  public TrivLexer(String source)
  {

    super(source, res);
    
  }
  
  @Override
	public Symbol nextSymbol()
	{
  	
  	Character c;
  	String symbolValue = "";
  	Symbol symbol = null;
  	
    while (charIndex < source.length()) {
    	
    	symbolValue = "";
  		c = nextChar();
  		
  		if (c == '"') {
  			symbol = new Symbol(dQuote(c), "stringLiteral");
  			break;
  		}
  		
  		else if (Character.isLetter(c)) {
  			symbolValue = identifier(c);
  			System.out.println(symbolValue);
  			if (isBoolean(symbolValue)) {
    			Boolean value = Boolean.parseBoolean(symbolValue);
    			symbol = new Symbol(value);
  			}
  			if (isReserved(symbolValue)) {
  				symbol = new Symbol(symbolValue, symbolValue);
  			}
  			else {
  				symbol = new Symbol(symbolValue, "identifier");
  			}
  			break;
  		}
  		
  		else if (Character.isDigit(c)) {
  			int value = Integer.parseInt(integer(c));
  			symbol = new Symbol(value);
    		break;
  		}
  		
  		else if (!Character.isWhitespace(c)) {
  			symbolValue = punctuator(c);
  			symbol = new Symbol(symbolValue, symbolValue);
  			break;
      }
  		
    }
 
    return symbol;
    
	}

}
