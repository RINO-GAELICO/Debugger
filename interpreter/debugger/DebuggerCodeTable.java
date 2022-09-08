package interpreter.debugger;

import java.util.HashMap;

import interpreter.CodeTable;
import interpreter.bytecode.debuggercodes.CallDebuggerCode;
import interpreter.bytecode.debuggercodes.FormalDebuggerCode;
import interpreter.bytecode.debuggercodes.FunctionDebuggerCode;
import interpreter.bytecode.debuggercodes.LineDebuggerCode;
import interpreter.bytecode.debuggercodes.LitDebuggerCode;
import interpreter.bytecode.debuggercodes.PopDebuggerCode;
import interpreter.bytecode.debuggercodes.ReturnDebuggerCode;

public class DebuggerCodeTable {
  private static HashMap<String, String> codeMap = new HashMap<>();

  public static void init() {
    CodeTable.init();
    codeMap.put( "CALL", (new CallDebuggerCode()).getClassName() );
    codeMap.put( "FORMAL", (new FormalDebuggerCode()).getClassName());
    codeMap.put( "FUNCTION", (new FunctionDebuggerCode()).getClassName());
    codeMap.put( "LINE", (new LineDebuggerCode()).getClassName());
    codeMap.put( "LIT", (new LitDebuggerCode()).getClassName());
    codeMap.put( "POP", (new PopDebuggerCode()).getClassName());
    codeMap.put( "RETURN", (new ReturnDebuggerCode()).getClassName());
    
  }

  public static String get(String code) {

    String result = codeMap.get(code);
    // System.out.println("THIS IS THE RESULT: "+result);

    if(result == null) {
      return CodeTable.get(code);
    } else {
      return result;
    }
  }
}