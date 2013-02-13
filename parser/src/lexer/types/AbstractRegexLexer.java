package lexer.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractRegexLexer
{
	
	protected Pattern      identifier, numeric, decimal, hex;
	protected Matcher      matchId, matchNum, matchDec, matchHex, matchDQuote, matchNewLine;
	protected String       source;
	private   String[]     reservedWords;
	private   Symbol       currentSymbol;
  
  public AbstractRegexLexer(String s, String[] res)
  {

  	identifier = Pattern.compile("[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z]");
  	numeric    = Pattern.compile("\\d");
  	decimal    = Pattern.compile("(0|[1-9][0-9]*)");
  	hex        = Pattern.compile("(0x|0X)[a-fA-F0-9]+");
  	
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
  		error("error: [" + currentSymbol.getValue() + "]" + " found where [" + s + "] expected.");
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
  
  
  protected void slice(int index)
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
	
	
	
	protected String dQuote()
	{
		
		String value = "\"";
		int end = -1;
		slice(1);
		
		if (matchDQuote.find()) {
		  end = matchDQuote.end();
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
		
		matchNewLine = Pattern.compile("\n").matcher(value);
		
		if (matchNewLine.find() && matchNewLine.start() != -1) {
			error("lexical error - string extends over line break");
		}
		return value;
		
	}
	
	
  
  protected String identifier()
	{
  	
  	String value = "";
  	value = matchId.group(0);
  	slice(matchId.end());
  	return value;
		
	}
  
  
  
  protected Integer integer()
	{
  	
  	String value = "";
  	value = matchNum.group(0);
  	slice(matchNum.end());
  	return Integer.parseInt(value);
		
	} 
  
  
  
  protected void updateMatchers()
  {
  	
  	matchId      = identifier.matcher(source);
  	matchNum     = numeric.matcher(source);
  	matchDQuote  = Pattern.compile("\"").matcher(source);
  	
  }

}
