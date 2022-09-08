package interpreter.bytecode.debuggercodes;

import java.util.*;

import interpreter.VirtualMachine;
import interpreter.bytecode.FormalCode;

public class FormalDebuggerCode extends FormalCode {


    @Override
    public void execute(VirtualMachine dvm) {
        
        dvm.formalDebuggerMethod(this);
    }


    public String getClassName(){
        return "interpreter.bytecode.debuggercodes.FormalDebuggerCode";
    }

    @Override
    public String toString() {

        return super.toString();
    }
    
}
