package lexer.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractRegexLexer
{
	
	protected Pattern      identifier, numeric, decimal, hex;
	protected Matcher      matchId, matchNum, matchDec, matchHex;
	protected Integer      charIndex;
	protected String       source;
	private   String[]     reservedWords;
	private   Symbol       currentSymbol;
  
  public AbstractRegexLexer(String s, String[] res)
  {

  	identifier = Pattern.compile("[a-zA-z0-9]+");
  	numeric    = Pattern.compile("\\d");
  	decimal    = Pattern.compile("(0|[1-9][0-9]*)");
  	hex        = Pattern.compile("(0x|0X)[a-fA-F0-9]+");
  	
  	charIndex       = 0;
  	source          = s;
  	reservedWords   = res;
  	
  	updateMatchers();
    
  }
  
    
  protected abstract void nextSymbol();
  
  
  public void initialise()
  {
  	
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
  		error("error: \"" + currentSymbol.getValue() + "\"" + " found where \"" + s + "\" expected.");
  	}
  	
  }
  
  
  public Symbol getCurrentSymbol()
  {
  	
  	return currentSymbol;
  	
  }
  
  
  protected void setCurrentSymbol(Symbol symbol)
  {
  	
  	currentSymbol = symbol;
  	
  }
  
  
  protected void strip(int index)
  {
  	
  	source = source.substring(index).trim();
  	updateMatchers();
  	
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
	
	
	protected String punctuator(Character c)
	{

		switch(c) {		
	  case '=' : strip(1); return c.toString();
	  case '+' : return c.toString();
	  case '*' : return c.toString();
	  case '/' : return c.toString();
	  case '-' : return c.toString();
	  default  : return "lexical error - illegal character";
	  }
		
	}
	
	
	
	protected boolean isBoolean(String symbolValue)
	{
		
		return (symbolValue.equals("true") || symbolValue.equals("false"));
		
	}
	
	
	
	protected String dQuote()
	{
		
		String value = "";
		value = addChar(value, c);
		
		while (charIndex < source.length()) {
			c = nextChar();
			if (c == '"') {
				value = addChar(value, c);
				break;				
			}
			else value = addChar(value, c);
		}		
		return value;
		
	}
	
	
  
  protected String identifier()
	{
  	
  	String value = "";
  	value = matchId.group(0);
  	strip(matchId.end());
  	return value;
		
	}
  
  
  
  protected Integer integer()
	{
  	
  	String value = "";
  	value = matchNum.group(0);
  	strip(matchNum.end());
  	return Integer.parseInt(value);
		
	} 
  
  
  protected Character nextChar()
  {
  	
  	return source.charAt(charIndex++);
  	
  }  
  
  
  protected String addChar(String s, Character c)
  {

  	return s + c;
  	
  }
  
  protected void updateMatchers()
  {
  	
  	matchId  = identifier.matcher(source);
  	matchNum = numeric.matcher(source);
  	
  }

}
