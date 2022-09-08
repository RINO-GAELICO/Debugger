package interpreter.debugger;

import java.util.*;

public class FunctionEnvironmentRecord {

  private HashMap<String,Binder> symbols = new HashMap<String,Binder>();

  private String top;    // reference to last symbol added to
                         // current scope; this essentially is the
                         // start of a linked list of symbols in scope
  private Binder marks;  // scope mark; essentially we have a stack of
                         // functions - push for new scope; pop when closing
                         // scope
  private String functionName;
  private Integer start, end, currentLineNumber;
  

  public void beginScope() {

    marks = new Binder(null,top,marks);
    top=null;

  }

  public void setFunctionInfo(String functionName, int startingLineNumber, int endingLineNumber) {

    this.functionName = functionName;
    this.start = startingLineNumber;
    this.end = endingLineNumber;

  }

  public void setCurrentLineNumber(int currentLineNumber) {

    this.currentLineNumber = currentLineNumber;
  }

  public Integer getCurrentLine(){
    return currentLineNumber;

  }

  public void enter(String symbol, int value) {

    // FORMAL xyz n — enter(“xyz”, n)
    // LIT 0 i — enter(“i”, currentOffset)
    symbols.put(symbol, new Binder(value, top, symbols.get(symbol)));
	  top = symbol;

  }

  public void pop(int count) {
    // POP n — delete the last n items entered into the symbol table 
    for(int i = 0; i<count ; i++){

      Binder tempBinder = symbols.get(top);

      if (tempBinder.getTail()!=null){
        symbols.put(top,tempBinder.getTail());
      }else{
        symbols.remove(top);
      }

      top = tempBinder.getPrevtop();

      if(top==null){
        top=marks.getPrevtop();
        marks=marks.getTail();
      }

    }


  }

  public HashMap<String,Binder> getSymbols(){
    HashMap<String,Binder> tempSymbols = symbols;
    return tempSymbols;
  }

  public int getStart(){
    return start.intValue();
  }

  public int getEnd(){
    return end.intValue();
  }

  @Override
  public String toString(){
  
    StringBuilder wholeString = new StringBuilder("(");
    if(!symbols.isEmpty()){
      List<Map.Entry<String,Binder>> listPair = new ArrayList<>(symbols.entrySet());
      listPair.stream()
      .forEach((entry)->{
        wholeString.append( "<" + entry.getKey() + "/" + entry.getValue().getValue() +">, ");
        if(entry.getValue().getTail()!=null){
          wholeString.delete((wholeString.length()-3),wholeString.length());
          wholeString.append("-<"+entry.getKey()+","+ entry.getValue().getTail().getValue() +">, ");
        }
      } );
    }else{
      wholeString.append("<>, ");
    }
    if(start!=null){
      wholeString.append(start+", ");
    }else{
      wholeString.append("-, ");
    }
    if(end!=null){
      wholeString.append(end+", ");
    }else{
      wholeString.append("-, ");
    }
    if(functionName!=null){
      wholeString.append(functionName+", ");
    }else{
      wholeString.append("-, ");
    }
    if(currentLineNumber!=null){
      wholeString.append(currentLineNumber+")");
    }else{
      wholeString.append("-)");
    }
    
    return wholeString.toString();
  }

  /**
   * Utility method to test your implementation. The output should be:
   * (<>, -, -, -, -)
   * (<>, g, 1, 20, -)
   * (<>, g, 1, 20, 5)
   * (<a/4>, g, 1, 20, 5)
   * (<b/2, a/4>, g, 1, 20, 5)
   * (<b/2, a/4, c/7>, g, 1, 20, 5)
   * (<b/2, a/1, c/7>, g, 1, 20, 5)
   * (<b/2, a/4>, g, 1, 20, 5)
   * (<a/4>, g, 1, 20, 5)
   */
  public static void main(String[] args) {
    FunctionEnvironmentRecord record = new FunctionEnvironmentRecord();

    record.beginScope();
    System.out.println(record);

    record.setFunctionInfo("g", 1, 20);
    System.out.println(record);

    record.setCurrentLineNumber(5);
    System.out.println(record);

    record.enter("a", 4);
    System.out.println(record);

    record.enter("b", 2);
    System.out.println(record);

    record.enter("c", 7);
    System.out.println(record);

    record.enter("a", 1);
    System.out.println(record);

    record.pop(2);
    System.out.println(record);

    record.pop(1);
    System.out.println(record);
  }
}