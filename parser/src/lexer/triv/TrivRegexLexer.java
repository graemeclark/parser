package lexer.triv;

import lexer.types.AbstractRegexLexer;
import lexer.types.Symbol;


public class TrivRegexLexer extends AbstractRegexLexer
{

	private static String[] res = { "let", "in" };
	
  public TrivRegexLexer(String source)
  {

    super(source, res);
    
  }
  
  @Override
	protected void nextSymbol()
	{

  	Character c;
  	String symbolValue = "";
  	Symbol symbol = null;
  	
    while (charIndex < source.length()) {
    	
    	symbolValue = "";
  		
    	if (matchId.find(0) && matchId.start() == 0) {

  			symbolValue = identifier();
  			
  			if (isBoolean(symbolValue)) {
    			Boolean value = Boolean.parseBoolean(symbolValue);
    			symbol = new Symbol(value);
    			break;
  			}
  			if (isReserved(symbolValue)) {
  				symbol = new Symbol(symbolValue, symbolValue);
  				break;
  			}
  			else {
  				symbol = new Symbol(symbolValue, "identifier");
  				break;
  			}
  		}
    	
    	else if (matchNum.find(0) && matchNum.start() == 0) {
  			symbol = new Symbol(integer());
    		break;
  		}
    	
    	else if (matchDQuote.find(0) && matchDQuote.start() == 0) {
  			symbol = new Symbol(dQuote(), "stringLiteral");
    		break;
  		}
  		
  		else {
  			c = source.charAt(0);
  		  symbol = new Symbol(punctuator(c), c.toString());
  			break;
  		}
  		
    }
    setCurrentSymbol(symbol);
	}

}
