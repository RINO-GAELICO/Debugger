package interpreter.debugger.ui.commands;

import java.util.*;

import interpreter.debugger.Debugger;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.SourceFileEntry;


public abstract class DebuggerCommand {

  static Map<String, DebuggerCommand> commands = new HashMap<>();

  static {

    commands.put( "?", new QuestionCommand() );
    commands.put( "set", new SetCommand() );
    commands.put( "list", new ListCommand() );
    commands.put( "source", new SourceCommand() );
    commands.put( "step", new StepCommand() );
    commands.put( "continue", new ContinueCommand() );
    commands.put( "locals", new LocalsCommand() );
    commands.put( "exit", new ExitCommand() );
    
    


  }
  public abstract void execute(SourceFileEntry sourceFileEntries, DebuggerVirtualMachine dvm, Debugger debugger);

  public static DebuggerCommand get(String code) {
    return commands.get(code);
  }


}