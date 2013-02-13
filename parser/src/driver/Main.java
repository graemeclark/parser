package driver;

import parser.triv.TrivParser;
import parser.types.AbstractParser;

public class Main
{

  public static void main(String[] args)
  {
    
    String source = "let a = \"str\" in let b = 7 in";
    
    AbstractParser parser = new TrivParser(source);
    parser.parseProgram();

  }

}
