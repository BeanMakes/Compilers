// Do not modify the code below except for replacing the "..."!  Don't
// add anything (including "public" declarations), don't remove
// anything (including "public" declarations). Don't wrap it in a
// package, don't make it an innner class of some other class.  If
// your IDE suggsts to change anything below, ignore your IDE. You are
// welcome to add new classes! Please put them into separate files.

import java.util.ArrayList;
import java.util.List;

class SyntaxException extends Exception {
    public String msg;
    public SyntaxException ( String _msg ) { msg = _msg; } }

class Task3Exception extends Exception {
    public String msg;
    public Task3Exception ( String _msg ) { msg = _msg; } }

interface Parser {
    public Block parse ( List<Token> input ) throws SyntaxException, 
                                                      Task3Exception; }

class Task3 {
    public static Parser create () {
        Parser pass = new Parser() {
            @Override
            public Block parse(List<Token> input) throws SyntaxException, Task3Exception {
                if(input.isEmpty()){
                    return null;
                }
                else if(input.get(0).getClass() == T_LeftCurlyBracket.class && input.get(input.size()-1).getClass() == T_RightCurlyBracket.class){
                    input.remove(0);
                    input.remove(input.size()-1);
                    return(new Block(ene(input)));

                }
                else{
                    throw new SyntaxException("Invalid Syntax");
                }

            }
            public List<Exp> ene(List<Token> input) throws SyntaxException, Task3Exception {
                if(input.isEmpty()){
                    return new ArrayList<>();
                }
                else{
                    if(input.get(0).getClass() == T_Integer.class){
                        if(input.size()==1){
                            List<Exp> result = new ArrayList<>();
                            T_Integer tmp = (T_Integer)input.get(0);
                            result.add(new IntLiteral(tmp.n));
                            input.remove(0);
                            return result;
                        }
                        else{
                            if(input.get(1).getClass() == T_Semicolon.class){
                                List<Exp> result = new ArrayList<>();
                                input.remove(1);
                                T_Integer tmp = (T_Integer)input.get(0);
                                result.add(new IntLiteral(tmp.n));
                                input.remove(0);
                                result.addAll(ene(input));
                                return result;
                            } else{
                                List<Exp> result = new ArrayList<>();
                                T_Integer tmp = (T_Integer)input.get(0);
                                result.add(new IntLiteral(tmp.n));
                                input.remove(0);
                                if(!(input.isEmpty())){
                                    throw new SyntaxException("Invalid Syntax!");
                                }
                                return result;
                            }
                        }

                    } else if(input.get(0).getClass() == T_Skip.class){
                        if(input.size()==1){
                            List<Exp> result = new ArrayList<>();
                            result.add(new Skip());
                            input.remove(0);
                            return result;
                        } else {
                            if(input.get(1).getClass() == T_Semicolon.class){
                                List<Exp> result = new ArrayList<>();
                                input.remove(0);
                                result.add(new Skip());
                                input.remove(0);
                                result.addAll(ene(input));
                                return result;
                            } else {
                                List<Exp> result = new ArrayList<>();
                                result.add(new Skip());
                                input.remove(0);
                                if(!(input.isEmpty())){
                                    throw new SyntaxException("Invalid Syntax!");
                                }
                                return result;
                            }
                        }


                    } else if(input.get(0).getClass() == T_LeftCurlyBracket.class && input.get(input.size()-1).getClass() == T_RightCurlyBracket.class){
                        List<Exp> result = new ArrayList<>();
                        input.remove(0);
                        input.remove(input.size()-1);
                        result.add(new BlockExp(new Block(ene(input))));
                        return result;

                    } else if(input.get(0).getClass() == T_LeftCurlyBracket.class){
                        int i = 0;
                        while(i<input.size()){
                            if(input.get(i).getClass()==T_RightCurlyBracket.class && input.get(i+1).getClass()==T_Semicolon.class){

                                break;
                            }else{
                                i++;
                            }
                        }
                        if(i<input.size()&&i+1<input.size()){
                            List<Exp> result = new ArrayList<>();
                            List<Token> temp = new ArrayList<>();
                            for(int j = 1; j<i;j++){
                                temp.add(input.get(j));
                            }
                            for(int j = 0; j<temp.size(); j++){
                                input.remove(1);
                            }
                            System.out.println(input);
                            input.remove(0);
                            input.remove(0);
                            input.remove(0);
                            result.add(new BlockExp(new Block(ene(temp))));
                            result.addAll(ene(input));
                            return result;
                        }
                    }else{
                        throw new SyntaxException("Invalid Syntax!");

                    }
                }
                throw new SyntaxException("Invalid Syntax!");
            }
        };
        return pass;
    } }




