package interpreter.debugger.ui.commands;

import java.util.*;

import interpreter.debugger.Debugger;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.SourceFileEntry;

public class QuestionCommand extends DebuggerCommand{

    @Override
    public void execute(SourceFileEntry sourceFileEntries, DebuggerVirtualMachine dvm, Debugger debugger){

        List<Map.Entry<String,DebuggerCommand>> listPair = new ArrayList<>(DebuggerCommand.commands.entrySet());

        listPair.stream()
        .sorted(Comparator.comparing((entry)-> entry.getKey()))
        .filter(entry->!(entry.getKey().equals("?")))
        .forEach(
            (entry)->{
                System.out.println(entry.getKey());
            }
        );

        return;
    }
    
}
