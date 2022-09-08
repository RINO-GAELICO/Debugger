package interpreter.debugger.ui.commands;

import java.util.Scanner;

import interpreter.debugger.Debugger;

public class DebuggerShell {

  private Scanner input = new Scanner(System.in);  
  private DebuggerCommand commandUser;
  
  public DebuggerShell(Debugger debugger) {
    
  }

  public Scanner getInput() {
    return input;
  }

  public DebuggerCommand prompt() {

    System.out.println("Type ? for help");
    System.out.print(">");

    
    String commandString = input.next();
    commandUser = DebuggerCommand.commands.get(commandString);
    
    return commandUser;
  }

  
}