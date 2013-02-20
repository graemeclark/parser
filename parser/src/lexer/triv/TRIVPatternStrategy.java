package lexer.triv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lexer.types.PatternStrategy;

public class TRIVPatternStrategy implements PatternStrategy
{
	
	protected static Pattern identifier = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
	protected static Pattern numeric    = Pattern.compile("\\d");
	
	public Matcher matchId, matchNum, matchDQuote, matchNewLine;

	@Override
	public void setMatchers(String source)
	{
		
		matchId       = identifier.matcher(source);
  	matchNum      = numeric.matcher(source);
  	matchDQuote   = Pattern.compile("\"").matcher(source);
  	matchNewLine  = Pattern.compile("\n").matcher(source);
		
	}

	@Override
	public void setIntegerPattern(String pattern)
	{
		
		numeric = Pattern.compile(pattern);
		
	}

	@Override
	public void setIdentifierPattern(String pattern)
	{
		
		identifier = Pattern.compile(pattern);
		
	}

	@Override
	public Boolean hasNum()
	{
		
		return matchNum.find() && matchNum.start() == 0;
		
	}

	@Override
	public Boolean hasId()
	{

		return (matchId.find() && matchId.start() == 0);
		
	}

	@Override
	public Boolean hasDQuote()
	{
		
		return matchDQuote.find() && matchDQuote.start() == 0;
		
	}

	@Override
	public Boolean hasNewLine()
	{
		
		return matchNewLine.find() && matchNewLine.start() != -1;
		
	}

	@Override
	public Integer closingDQuoteIndex()
	{
		
		return matchDQuote.end();
		
	}

	@Override
	public Integer idEnd()
	{
		
		return matchId.end();
		
	}

	@Override
	public Integer numEnd()
	{
		
		return matchNum.end();
		
	}

	@Override
	public String matchingId()
	{
		
		return matchId.group(0);
		
	}

	@Override
	public String matchingNum()
	{
		
		return matchNum.group(0);
		
	}

	@Override
	public Matcher getId()
	{
		return matchId;
	}

	@Override
	public Boolean findDQuote()
	{
		
		return matchDQuote.find();
		
	}

}
