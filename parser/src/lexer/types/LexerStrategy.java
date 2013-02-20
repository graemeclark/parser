package lexer.types;

public interface LexerStrategy
{
	
	public void initialise(String s);
	
	public Boolean have(String s);
	
	public void mustBe(String s);
	
	public void setPatternStrategy(PatternStrategy p);
	
	public void nextSymbol();
	
	public void setCurrentSymbol(Symbol s);
	
	public Symbol getCurrentSymbol();
	
	public String  punctuator(Character c);
	
	public String  identifier();
	
	public Integer integer();
	
	public String  dQuote();

}
