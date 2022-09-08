package interpreter.debugger;

/** <pre>
 *  Binder objects group 3 fields
 *  1. a value
 *  2. the next link in the chain of symbols in the current scope
 *  3. the next link of a previous Binder for the same identifier
 *     in a previous scope
 *  </pre>
*/
public class Binder {

    private Integer value;
    private String prevtop;   // prior symbol in same scope
    private Binder tail;      // prior binder for same symbol
                              // restore this when closing scope

    public Binder(Integer v, String p, Binder t) {
      value=v; prevtop=p; tail=t;
    }
  
    public int getValue() { 
        return value.intValue(); 
    }

    public  String getPrevtop() { 
        return prevtop; 
    }

    public Binder getTail() { 
        return tail; 
    }
}
  