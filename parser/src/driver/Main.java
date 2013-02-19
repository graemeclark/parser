package driver;

import parser.types.TRIVParserStrategy;

public class Main
{

  public static void main(String[] args)
  {
    
    String source = "let a = \"str\" in let b = 7 in";
    
    Compiler compiler = new Compiler();
    compiler.setParserStrategy(new TRIVParserStrategy());

    compiler.compile(source);

  }

}
