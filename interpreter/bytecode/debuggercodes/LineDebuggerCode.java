package interpreter.bytecode.debuggercodes;


import java.util.Formatter;

import interpreter.VirtualMachine;
import interpreter.bytecode.LineCode;

public class LineDebuggerCode extends LineCode {


    @Override
    public void execute(VirtualMachine dvm) {
        
        dvm.lineCodeMethod(this);
        
    }


    public String getClassName(){
        return "interpreter.bytecode.debuggercodes.LineDebuggerCode";
    }

    @Override
    public String toString(){
        
        return super.toString();
    }




    
}

