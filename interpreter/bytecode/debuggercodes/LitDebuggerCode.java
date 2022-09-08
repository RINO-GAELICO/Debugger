package interpreter.bytecode.debuggercodes;

import java.util.Formatter;

import interpreter.VirtualMachine;
import interpreter.bytecode.LitCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class LitDebuggerCode extends LitCode {


    @Override
    public void execute(VirtualMachine dvm) {
        
        dvm.litDebuggerCode(this);
    }

    @Override
    public String toString(){
        
        return super.toString();
    }

    public String getClassName(){
        return "interpreter.bytecode.debuggercodes.LitDebuggerCode";
    }
}

    

