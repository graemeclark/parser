package lexer.types;

public abstract class AbstractLexer
{
	
	protected Integer      charIndex;
	protected String       source;
	protected String[]     reservedWords;
  
  public AbstractLexer(String s, String[] res)
  {
  	
  	charIndex       = 0;
  	source          = s;
  	reservedWords   = res;
    
  }
  
  
  
  public abstract Symbol nextSymbol(); 
  
  
  public Boolean have(String s)
  {
  	
  	int temp = charIndex;
  	Symbol symbol = nextSymbol();
  	//System.out.println(symbol);
  	
  	if (symbol != null && s.equals(symbol.getType())) {
  		//charIndex = temp;
  		return true;
  	}
  	else {
  		return false;
  	}
  	
  }
  
  
  
  public Symbol mustBe(String s)
  {
  	
  	Symbol symbol = nextSymbol();
  	if (symbol == null) {
  		error("END OF PROGRAM");
  		return null;
  	}
  	else if (s.equals(symbol.getType())) {
  		//symbol = nextSymbol();
  		return symbol;
  	}
  	else {
  		error("(" + symbol.getValue() + ")" + " found where " + s + " expected");
  		return null;
  	}
  	
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
		//System.exit(0);
		
	}
	
	
	protected String punctuator(Character c)
	{
		
		switch(c) {		
	  case '=' : return c.toString();
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
	
	
	
	protected String dQuote(Character c)
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
	
	
  
  protected String identifier(Character c)
	{
  	
  	String value = "";
  	value = addChar(value, c);
		
  	while (charIndex < source.length()) {
  		c = nextChar();
  		if (Character.isLetter(c) || Character.isDigit(c)) {
  			value = addChar(value, c);
  		}
  		else {
  			charIndex--;
  			break;
  		}
  	}  	
  	return value;
		
	}
  
  
  
  protected String integer(Character c)
	{
  	
  	String value = "";
  	value = addChar(value, c);
  	
  	while (charIndex < source.length()) {  		
  		c = nextChar();		
  		if (Character.isDigit(c)) {  			
  			value = addChar(value, c);
  		}  		
  		else if (!Character.isDigit(c) && !Character.isWhitespace(c)) { 			
  			error("Can't start an identifier with an integer.");
  		}  		
  		else {
  			charIndex--;
  			break;
  		}
  	} 	
  	return value;
		
	} 
  
  
  protected Character nextChar()
  {
  	
  	return source.charAt(charIndex++);
  	
  }
  
  
  
  protected String addChar(String s, Character c)
  {

  	return s + c;
  	
  }  

}
