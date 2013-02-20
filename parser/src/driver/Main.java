package driver;

import lexer.triv.TRIVLexerStrategy;
import lexer.triv.TRIVPatternStrategy;
import parser.triv.TRIVParserStrategy;

public class Main
{

  public static void main(String[] args)
  {
    
    String source = "let a = \"hello\" in let b = 7 in";
    
    Compiler compiler = new Compiler();
    compiler.setParserStrategy(new TRIVParserStrategy());
    compiler.setLexerStrategy(new TRIVLexerStrategy());
    compiler.setPatternStrategy(new TRIVPatternStrategy());

    compiler.compile(source);

  }

}
