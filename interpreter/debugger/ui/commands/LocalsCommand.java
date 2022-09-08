package interpreter.debugger.ui.commands;

import java.util.*;

import interpreter.debugger.Binder;
import interpreter.debugger.Debugger;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.FunctionEnvironmentRecord;
import interpreter.debugger.SourceFileEntry;

public class LocalsCommand extends DebuggerCommand{

    @Override
    public void execute(SourceFileEntry sourceFileEntries, DebuggerVirtualMachine dvm, Debugger debugger) {
        
        StringBuilder symbolsString = new StringBuilder();
        
        if(!dvm.getStackFunctions().isEmpty()){
            FunctionEnvironmentRecord currentFunction= dvm.getStackFunctions().peek();
            HashMap<String,Binder> symbols = currentFunction.getSymbols();
        
            List<Map.Entry<String,Binder>> listPair = new ArrayList<>(symbols.entrySet());
            listPair.stream()
            .forEach((entry)->{
            int offsetValue = entry.getValue().getValue();
            symbolsString.append(entry.getKey() + ":" + dvm.getValueFromStack(offsetValue));
            symbolsString.append("\n");
        } );
        if(symbolsString.length()>0){
            symbolsString.delete(symbolsString.length()-1, symbolsString.length());
        }
        System.out.println(symbolsString.toString());
        }
        
        
    }

}
