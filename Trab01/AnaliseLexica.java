import java.io.*;


enum TokenType{ NUM, SOMA, MULT, SUB, DIV, APar,FPar, EOF}


class Token{
  String lexema;
  TokenType token;

 Token (String l, TokenType t)
 	{ lexema=l;token = t;}	

}

class AnaliseLexica {

    private BufferedReader arquivo;

	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}

	Token getNextToken() throws Exception
	{	
		int eof = -1;
		char currchar;
		int currchar1;
		String textString = "";

			do{
				currchar1 =  arquivo.read();
				currchar = (char) currchar1;
				if (currchar >= '0' && currchar <= '9'){
					textString = textString.concat(String.valueOf(currchar));
					do{
						arquivo.mark(1);
						currchar1 =  arquivo.read();
						currchar = (char) currchar1;

						if(currchar >= '0' && currchar <= '9'){
						
							textString = textString.concat(String.valueOf(currchar));
						
						}
						else{
							arquivo.reset();
						}
					}while(currchar >= '0' && currchar <= '9');
					
					return (new Token (textString, TokenType.NUM));
				}
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			textString = "";
			if(currchar1 != eof && currchar1 !=10)
			{
					switch (currchar){
						case '(':
							return (new Token (String.valueOf(currchar),TokenType.APar));
						case ')':
							return (new Token (String.valueOf(currchar),TokenType.FPar));
						case '+':
							return (new Token (String.valueOf(currchar),TokenType.SOMA));
						case '*':
							return (new Token (String.valueOf(currchar),TokenType.MULT));
						case '-':
							return (new Token (String.valueOf(currchar),TokenType.SUB));
						case '/':
							return (new Token (String.valueOf(currchar),TokenType.DIV));
						
						default: throw (new Exception("Caractere inválido: " + String.valueOf(currchar)));
					}
			}

			arquivo.close();
			
		return (new Token(String.valueOf(currchar),TokenType.EOF));
		
	}
}