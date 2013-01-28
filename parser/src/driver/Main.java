package driver;

import lexer.triv.TrivLexer;
import lexer.types.AbstractLexer;

public class Main
{

  public static void main(String[] args)
  {
    
    String source = "let var1 = true in let var2 = 7 in var1 + var2";
    
    AbstractLexer lex = new TrivLexer(source);
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    System.out.println(lex.nextSymbol());
    
  }

}
