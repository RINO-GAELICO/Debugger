package interpreter.debugger.ui.commands;

import java.util.Scanner;

import interpreter.debugger.Debugger;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.SourceFileEntry;

public class SetCommand extends DebuggerCommand {

    private Scanner inputNumber = new Scanner(System.in);

    @Override
    public void execute(SourceFileEntry sourceFileEntries, DebuggerVirtualMachine dvm, Debugger debugger){

        System.out.println("Enter line number:");
        System.out.print(">");
        try{
            String commandNumber = inputNumber.next();
            int lineNumber = Integer.parseInt(commandNumber);
            sourceFileEntries.setBreakPoint(lineNumber);
            dvm.setBreakPoint(lineNumber);
        }catch(NumberFormatException e){
            System.out.println("Not a valid input");
        }
        
        return;
    }

}
