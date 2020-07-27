%%
%public
%class TokenLexer

LineTerminator = [ \n\t\r]+
Whitespace     = {LineTerminator}
Integer = [0-9]+
Identifier =[:jletter:] [:jletterdigit:]*
%%

/*Numbers*/
{Integer}	{return new T_Integer(Integer.parseInt(yytext()));}



/*Operations*/
<YYINITIAL> ";"		{return new T_Semicolon();}
<YYINITIAL> "("		{return new T_LeftBracket();}
<YYINITIAL> ")"		{return new T_RightBracket();}
<YYINITIAL> "=="	{return new T_Equal();}
<YYINITIAL> "<"		{return new T_LessThan();}
<YYINITIAL> ">"		{return new T_GreaterThan();}
<YYINITIAL> "<="	{return new T_LessEq();}
<YYINITIAL> ">="	{return new T_GreaterEq();}
<YYINITIAL> "="		{return new T_EqualDefines();}
<YYINITIAL> ","		{return new T_Comma();}
<YYINITIAL> "{"		{return new T_LeftCurlyBracket();}
<YYINITIAL> "}"		{return new T_RightCurlyBracket();}
<YYINITIAL> ":="	{return new T_Assign();}
<YYINITIAL> "+"		{return new T_Plus();}
<YYINITIAL> "*"		{return new T_Times();}
<YYINITIAL> "-"		{return new T_Minus();}
<YYINITIAL> "/"		{return new T_Div();}


/*Keywords*/
<YYINITIAL> "def"	{return new T_Def();}
<YYINITIAL> "skip"	{return new T_Skip();}
<YYINITIAL> "if"	{return new T_If();}
<YYINITIAL> "then"	{return new T_Then();}
<YYINITIAL> "else"	{return new T_Else();}
<YYINITIAL> "while"	{return new T_While();}
<YYINITIAL> "do"	{return new T_Do();}
<YYINITIAL> "repeat"	{return new T_Repeat();}
<YYINITIAL> "until"	{return new T_Until();}
<YYINITIAL> "break"	{return new T_Break();}
<YYINITIAL> "continue"	{return new T_Continue();}

{Identifier}	{return new T_Identifier(yytext());}

<YYINITIAL>{Whitespace}	{}




