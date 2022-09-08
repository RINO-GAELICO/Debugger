package interpreter.debugger.ui.commands;

import interpreter.debugger.Debugger;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.SourceFileEntry;

public class ExitCommand extends DebuggerCommand{

    @Override
    public void execute(SourceFileEntry sourceFileEntries, DebuggerVirtualMachine dvm, Debugger debugger) {
        debugger.setIsRunning(false);
        
    }

}
