package interpreter.bytecode.debuggercodes;


import interpreter.VirtualMachine;
import interpreter.bytecode.PopCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class PopDebuggerCode extends PopCode {



    @Override
    public void execute(VirtualMachine dvm) {

        dvm.popDebuggerMethod(this);
    }

    public String getClassName(){
        return "interpreter.bytecode.debuggercodes.PopDebuggerCode";
    }
    
}

