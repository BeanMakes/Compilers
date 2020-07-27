import java.util.*;
import java.io.*;
class Task2 {
  public static Lexer create () {
    Lexer lexer = new Lexer() {
      @java.lang.Override
      public List<Token> lex(String input) throws LexicalException, Task2Exception {
        List<Token> result = new ArrayList<Token>();
        StringReader reader = new StringReader(input);
        TokenLexer tokLex = new TokenLexer(reader);
        try {
          Token token = tokLex.yylex();
          while (token != null) {
            result.add(token);
            token = tokLex.yylex();
          }
        } catch (Exception e){
          throw new LexicalException("Invalid");
        }
        return result;
        }
      };
    return lexer;
    }

  }