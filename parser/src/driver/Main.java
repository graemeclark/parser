package driver;

import parser.triv.TrivParser;
import parser.types.AbstractParser;

public class Main
{

  public static void main(String[] args)
  {
    
    String source = "let var1 = 4 = let var2 = 7 in";
    
    AbstractParser parser = new TrivParser(source);
    parser.parseProgram();

  }

}
