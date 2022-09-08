package interpreter.debugger.ui.commands;

import interpreter.debugger.Debugger;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.Entry;
import interpreter.debugger.SourceFileEntry;

public class ListCommand extends DebuggerCommand{

    @Override
    public void execute(SourceFileEntry sourceFileEntries, DebuggerVirtualMachine dvm, Debugger debugger) {
        StringBuilder sourceCodeString = new StringBuilder();

        for (Entry entry : sourceFileEntries.getEntriesArray()) {
            if(entry.getIsBreakpointLine()){
                sourceCodeString.append(sourceFileEntries.buildALine(entry));
                sourceCodeString.append("\n");
            }
           
        }
        
        System.out.println(sourceCodeString.toString());
        
    }

}
