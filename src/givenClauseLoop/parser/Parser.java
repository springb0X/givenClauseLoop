/* Generated By:JavaCC: Do not edit this line. Parser.java */
package givenClauseLoop.parser;

import java.util.List;
import java.util.LinkedList;
import givenClauseLoop.bean.FOLNode;
import givenClauseLoop.bean.SymType;

public class Parser implements ParserConstants {
        /**
	* Conjunctive Normal Form (CNF) Formulae's Parser.
	* Based on CNF fragment of TPTP syntax
	* http://www.tptp.org
	* http://www.cs.miami.edu/~tptp/TPTP/SyntaxBNF.html
	*
	*@param input CNF formulae
	*@param output
	*/
        public static void parsing(String input,Object output) throws Exception{
                try{
                        new Parser(new java.io.StringReader(input)).TPTP_file();
                }catch(Throwable e){
                        // Catching Throwable is ugly but JavaCC throws Error objects!
                        throw new ParseException("Syntax check failed: " + e.getMessage());
                }
    }

/**
* All the contents of the file
*/
  static final public void TPTP_file() throws ParseException {
    TPTP_input();
    jj_consume_token(0);
  }

/** 
 * Set of CNF annotated formulae
 */
  static final public void TPTP_input() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CNF:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      cnf_annotated();
    }
  }

/**
 * An annotated CNF formula
 */
  static final public void cnf_annotated() throws ParseException {
    jj_consume_token(CNF);
    jj_consume_token(OPEN_BRACKET);
    name();
    jj_consume_token(COMMA);
    formula_role();
    jj_consume_token(COMMA);
    cnf_formula();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      annotations();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(CLOSE_BRACKET);
    jj_consume_token(DOT);
  }

/**
* The CNF name
*/
  static final public void name() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOWER_WORD:
      jj_consume_token(LOWER_WORD);
      break;
    case SINGLE_QUOTED:
      jj_consume_token(SINGLE_QUOTED);
      break;
    case INTEGER:
      jj_consume_token(INTEGER);
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/**
* The CNF formula role
*/
  static final public void formula_role() throws ParseException {
    jj_consume_token(LOWER_WORD);
  }

/**
* The CNF annotations
*/
  static final public void annotations() throws ParseException {
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOWER_WORD:
        jj_consume_token(LOWER_WORD);
        break;
      case UPPER_WORD:
        jj_consume_token(UPPER_WORD);
        break;
      case SINGLE_QUOTED:
        jj_consume_token(SINGLE_QUOTED);
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOWER_WORD:
      case UPPER_WORD:
      case SINGLE_QUOTED:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
    }
  }

/****************************************************************
**  CNF FORMULAE (variables implicitly universally quantified) **
****************************************************************/
  static final public void cnf_formula() throws ParseException {
          FOLNode n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPEN_BRACKET:
      jj_consume_token(OPEN_BRACKET);
      n = literal();
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case VLINE:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_3;
        }
        jj_consume_token(VLINE);
        n = literal();
      }
      jj_consume_token(CLOSE_BRACKET);
      break;
    case NOT:
    case LOWER_WORD:
    case SINGLE_QUOTED:
      literal();
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case VLINE:
          ;
          break;
        default:
          jj_la1[6] = jj_gen;
          break label_4;
        }
        jj_consume_token(VLINE);
        literal();
      }
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }

  }

  static final public FOLNode literal() throws ParseException {
          Token t1=null, t2;
          List<FOLNode> args=null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      t1 = jj_consume_token(NOT);
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOWER_WORD:
      t2 = jj_consume_token(LOWER_WORD);
      break;
    case SINGLE_QUOTED:
      t2 = jj_consume_token(SINGLE_QUOTED);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPEN_BRACKET:
      jj_consume_token(OPEN_BRACKET);
      args = arguments();
      jj_consume_token(CLOSE_BRACKET);
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
                FOLNode n=new FOLNode((t1==null)? t2.image: t1.image + t2.image , SymType.PREDICATE);
                if(args!=null)
                        n.setArgs(args);
                {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  static final public List<FOLNode> arguments() throws ParseException {
          List<FOLNode> args=new LinkedList<FOLNode>();
          FOLNode n;
    n = term();
                  args.add(n);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_5;
      }
      jj_consume_token(COMMA);
      n = term();
                                                     args.add(n);
    }
          {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

/***************************************************
***************		TERM	************************
***************************************************/
  static final public FOLNode term() throws ParseException {
          Token t1=null, t2=null;
          List<FOLNode> args=null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case UPPER_WORD:
      t1 = jj_consume_token(UPPER_WORD);
                // VARIABLE
                {if (true) return new FOLNode(t1.image, SymType.VARIABLE);}
      break;
    case LOWER_WORD:
    case SINGLE_QUOTED:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOWER_WORD:
        t1 = jj_consume_token(LOWER_WORD);
        break;
      case SINGLE_QUOTED:
        t1 = jj_consume_token(SINGLE_QUOTED);
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPEN_BRACKET:
        jj_consume_token(OPEN_BRACKET);
        args = arguments();
        jj_consume_token(CLOSE_BRACKET);
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
                if(args==null) // CONSTANT
                        {if (true) return new FOLNode(t1.image, SymType.CONSTANT);}
                else            // FUNCTION
                {
                        FOLNode n=new FOLNode(t1.image, SymType.FUNCTION);
                        n.setArgs(args);
                        {if (true) return n;}
                }
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[15];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1000,0x800,0x200a000,0xe000,0xe000,0x40,0x40,0xa180,0x80,0xa000,0x100,0x800,0xa000,0x100,0xe000,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[32];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 15; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 32; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
