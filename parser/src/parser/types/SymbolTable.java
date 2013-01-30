package parser.types;

import java.util.Map;
import java.util.HashMap;

import lexer.types.Symbol;

public class SymbolTable
{
	
	Map<String, Object> table = new HashMap<String, Object>();
	
	
	public void put(String key, Object value)
	{
		
		table.put(key, value);
	
	}
	
	public Object get(String key)
	{
		
		return table.get(key);
		
	}
	
	public Symbol lookup(Symbol symbol)
	{
		
		if (table.containsKey(symbol.getValue())) {			
			return symbol;	
		}
		else {
			return null;
		}
		
	}
	
	public String toString()
	{
		
		String output = "";
		
		for (String name: table.keySet()){

      String key = name.toString();
      String value = table.get(name).toString();  
      output = output + key + " = " + value + "\n";


    }
		
		return output;
		
	}

}
