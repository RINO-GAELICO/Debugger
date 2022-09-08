package interpreter.debugger;

import java.io.IOException;

import interpreter.Interpreter;
import interpreter.Program;
import interpreter.debugger.ui.commands.DebuggerShell;

public class Debugger extends Interpreter {

  private DebuggerShell shell;
  private boolean isRunning;
  protected DebuggerByteCodeLoader byteCodeLoader;
  protected SourceCodeLoader sourceCodeLoader;
  private SourceFileEntry sourceFileEntries;
  

  public Debugger(String baseFileName) {

    DebuggerCodeTable.init();

    try {
      
      sourceCodeLoader = new SourceCodeLoader(baseFileName);
      sourceFileEntries = sourceCodeLoader.loadCode();
      StringBuilder filename = new StringBuilder(baseFileName);
      filename.append(".x.cod");
      byteCodeLoader = new DebuggerByteCodeLoader(filename.toString());

    } catch (IOException e) {
      System.out.println("**** " + e);
    }
    // Update to add the correct extensions to the base file name to
    // load the byte code file, as well as to load the source file
  }

  @Override
  public void run() {
    shell = new DebuggerShell(this);
    isRunning =true;

    Program program = byteCodeLoader.loadCodes();
    DebuggerVirtualMachine dvm = new DebuggerVirtualMachine(program, this);

    System.out.println(sourceFileEntries.toString());

    while(isRunning){
      try{
        shell.prompt().execute(sourceFileEntries, dvm, this);
      }catch(NullPointerException e){
        System.out.println("Not a valid command");
      }
      
    }
    
  }

  public void setIsRunning(boolean b){
    isRunning =false;
  }
}