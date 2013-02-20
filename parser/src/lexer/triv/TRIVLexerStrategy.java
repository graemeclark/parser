package lexer.triv;

import lexer.types.LexerStrategy;
import lexer.types.PatternStrategy;
import lexer.types.Symbol;

public class TRIVLexerStrategy implements LexerStrategy
{
	private static String[] reservedWords = { "let", "in" };
	
	protected PatternStrategy patternStrategy;
	protected String          source;
	private   Symbol          currentSymbol;
  
  @Override
	public void nextSymbol()
	{

  	Character c;
  	String symbolValue = "";
  	Symbol symbol = null;    	
    symbolValue = "";
    
    if (source.length() > 0) {
    	    	  		
    	if (patternStrategy.hasId()) {
    		
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
    	
    	else if (patternStrategy.hasNum()) {
  			symbol = new Symbol(integer());
  		}
    	
    	else if (patternStrategy.hasDQuote()) {
  			symbol = new Symbol(dQuote(), "stringLiteral");
  		}
  		
  		else {
  			c = source.charAt(0);
  		  symbol = new Symbol(punctuator(c), c.toString());
  		}
    }
    System.out.println(symbol);
    setCurrentSymbol(symbol);
	}
    
  public void setPatternStrategy(PatternStrategy p)
  {
  	
  	this.patternStrategy = p;
  	
  }
  
  
  public void initialise(String s)
  {
  	
  	source = s;
  	patternStrategy.setMatchers(source);
  	nextSymbol();
  	
  }
  
  
  public Boolean have(String s)
  {
  	
  	if (currentSymbol != null && s.equals(currentSymbol.getType())) {
  		nextSymbol();
  		return true;
  	}
  	else {
  		return false;
  	}
  	
  }
  
  
  
  public void mustBe(String s)
  {
  	
  	if (currentSymbol == null) {
  		error("END OF PROGRAM");
  	}
  	else if (s.equals(currentSymbol.getType())) {
  		nextSymbol();
  	}
  	else {
  		error("error: [" + currentSymbol.getValue() + "]" + " found where [" + s + "] expected.");
  	}
  	
  }
  
  
  public Symbol getCurrentSymbol()
  {
  	
  	return currentSymbol;
  	
  }
  
  
  public void setCurrentSymbol(Symbol symbol)
  {
  	
  	currentSymbol = symbol;
  	
  }
  
  
  protected void slice(int index)
  {
  	
  	source = source.substring(index).trim();
  	patternStrategy.setMatchers(source);
  	
  }
  
  
  protected Boolean isReserved(String s)
  {
  	
  	Boolean bool = false;
  	for (int i = 0; i < reservedWords.length; i++) {
  		if (s.equals(reservedWords[i])) {
  			bool = true;
  			break;
  		}
  		
  	}
  	return bool;  	
  }
  
  
  
	protected void error(String msg)
	{

		System.out.println(msg);
		System.exit(0);
		
	}
	
	
	public String punctuator(Character c)
	{

		switch(c) {		
	  case '=' : slice(1); return c.toString();
	  case '+' : return c.toString();
	  case '*' : return c.toString();
	  case '/' : return c.toString();
	  case '-' : return c.toString();
	  default  : return "lexical error - illegal character: [" + c + "]";
	  }
		
	}
	
	
	
	protected boolean isBoolean(String symbolValue)
	{
		
		return (symbolValue.equals("true") || symbolValue.equals("false"));
		
	}
	
	
	
	public String dQuote()
	{
		
		String value = "\"";
		int end = -1;
		slice(1);
    if (patternStrategy.findDQuote()) {
		  end = patternStrategy.closingDQuoteIndex();
		}
		
		if (end != -1) {
		  value = value + source.substring(0, end);
		  if (source.length() == end + 1) {
			  source = "";
		  }
		
		  else {
		  	slice(end + 1);
		  }
		}
		
		if (patternStrategy.hasNewLine()) {
			error("lexical error - string extends over line break");
		}
		return value;
		
	}
	
	
  
  public String identifier()
	{
  	
  	String value = "";
  	value = patternStrategy.matchingId();
  	slice(patternStrategy.idEnd());
  	return value;
		
	}
  
  
  
  public Integer integer()
	{
  	
  	String value = "";
  	value = patternStrategy.matchingNum();
  	slice(patternStrategy.numEnd());
  	return Integer.parseInt(value);
		
	} 

}
