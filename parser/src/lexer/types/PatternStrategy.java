package lexer.types;

import java.util.regex.Matcher;

public interface PatternStrategy
{
	
	public void setMatchers(String source);
	
	public void setIntegerPattern(String pattern);
	
	public void setIdentifierPattern(String pattern);
	
	public Boolean hasNum();
	
	public Boolean hasId();
	
	public Boolean hasDQuote();
	
	public Boolean findDQuote();
	
	public Boolean hasNewLine();
	
	public Integer closingDQuoteIndex();
	
	public Integer idEnd();
	
	public Integer numEnd();
	
	public String matchingId();
	
	public String matchingNum();
	
	public Matcher getId();

}