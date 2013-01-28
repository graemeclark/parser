package lexer.types;

public class Symbol
{
	
	String type;
	
	Integer i;
	String  s;
	Boolean b;

	
	public Symbol(Integer integer)
	{
		
		i = integer;
		type = "numericLiteral";
		
	}
	
	public Symbol(String string)
	{
		
		s = string;
		type = "stringLiteral";
		
	}
	
	public Symbol(String string, String t)
	{
		
		s = string;
		type = t;
		
	}
	
	public Symbol(Boolean bool)
	{
		
		b = bool;
		type = "boolLiteral";
		
	}
	
	public String getType()
	{
		
		return type;
		
	}
	
	public String toString()
	{
		
		if (i != null)
			return i + "\t" + type;
		if (s != null)
			return s + "\t" + type;
		if (b != null)
			return b + "\t" + type;
		else return "";
		
	}
	
	

}
