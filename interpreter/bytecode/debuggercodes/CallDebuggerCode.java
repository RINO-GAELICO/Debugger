package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.CallCode;
import interpreter.debugger.DebuggerVirtualMachine;


public class CallDebuggerCode extends CallCode {



    @Override
    public void execute(VirtualMachine dvm) {
        
        dvm.callDebuggerMethod(this);
        
    }

    @Override
    public String getClassName(){
        return "interpreter.bytecode.debuggercodes.CallDebuggerCode";
    }

    @Override
    public String toString(){
        return super.toString();
    }

   
    
}

