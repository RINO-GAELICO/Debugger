package interpreter.debugger.ui.commands;

import interpreter.debugger.Debugger;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.FunctionEnvironmentRecord;
import interpreter.debugger.SourceFileEntry;

public class SourceCommand extends DebuggerCommand {

    @Override
    public void execute(SourceFileEntry sourceFileEntries, DebuggerVirtualMachine dvm, Debugger debugger) {

        if (!dvm.getStackFunctions().isEmpty()) {
            FunctionEnvironmentRecord currentFunction = dvm.getStackFunctions().peek();

            StringBuilder sourceCodeString = new StringBuilder();

            int start = currentFunction.getStart();
            int end = currentFunction.getEnd();

            for (int i = start; i <= end; i++) {
                sourceCodeString.append(sourceFileEntries.getSingleLine(i));
            }

            System.out.println(sourceCodeString.toString());
        }

        return;

    }

}
