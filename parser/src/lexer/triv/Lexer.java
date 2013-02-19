package lexer.triv;

import lexer.types.AbstractRegexLexer;
import lexer.types.Symbol;


public class Lexer extends AbstractRegexLexer
{

	private static String[] res = { "let", "in" };
	
  public Lexer(String source)
  {

    super(source, res);
    
  }
  
  @Override
	protected void nextSymbol()
	{

  	Character c;
  	String symbolValue = "";
  	Symbol symbol = null;    	
    symbolValue = "";
    
    if (source.length() > 0) {
  		
    	if (matchId.find() && matchId.start() == 0) {

  			symbolValue = identifier();
  			
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
  			
  		}
    	
    	else if (matchNum.find() && matchNum.start() == 0) {
  			symbol = new Symbol(integer());
  		}
    	
    	else if (matchDQuote.find() && matchDQuote.start() == 0) {
  			symbol = new Symbol(dQuote(), "stringLiteral");
  		}
  		
  		else {
  			c = source.charAt(0);
  		  symbol = new Symbol(punctuator(c), c.toString());
  		}
    }
  		
    setCurrentSymbol(symbol);
	}

}
