package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ReturnCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class ReturnDebuggerCode extends ReturnCode {

   
    @Override
    public void execute(VirtualMachine dvm) {
        
        dvm.returnDebuggerMethod(this);
    }

    @Override
    public String getClassName(){
        return "interpreter.bytecode.debuggercodes.ReturnDebuggerCode";
    }

}

