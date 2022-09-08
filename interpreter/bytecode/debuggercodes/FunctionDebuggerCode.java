package interpreter.bytecode.debuggercodes;

import java.util.Formatter;

import interpreter.VirtualMachine;
import interpreter.bytecode.FunctionCode;

public class FunctionDebuggerCode extends FunctionCode {


    @Override
    public void execute(VirtualMachine dvm) {
        dvm.functionDebuggerMethod(this);
        
    }

    public String getClassName(){
        return "interpreter.bytecode.debuggercodes.FunctionDebuggerCode";
    }

    @Override
    public String toString() {
        
        return super.toString();
    }
    
}
