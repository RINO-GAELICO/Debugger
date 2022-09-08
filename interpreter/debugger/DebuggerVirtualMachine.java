package interpreter.debugger;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.debuggercodes.CallDebuggerCode;
import interpreter.bytecode.debuggercodes.FormalDebuggerCode;
import interpreter.bytecode.debuggercodes.FunctionDebuggerCode;
import interpreter.bytecode.debuggercodes.LineDebuggerCode;
import interpreter.bytecode.debuggercodes.LitDebuggerCode;
import interpreter.bytecode.debuggercodes.PopDebuggerCode;
import interpreter.bytecode.debuggercodes.ReturnDebuggerCode;

public class DebuggerVirtualMachine extends VirtualMachine {

  private Debugger debugger;
  private Stack<FunctionEnvironmentRecord> functionEnvRecordStack;
  private Set<Integer> breakpoints;
  private boolean continueOn, paused;
  private int currentLineNumber, lastBreak, previousLineNumber;

  public DebuggerVirtualMachine(Program program, Debugger debugger) {
    super(program);
    breakpoints = new HashSet<>();
    this.debugger = debugger;
    this.functionEnvRecordStack = new Stack<>();
    super.setProgramCounter(0);
    paused = false;
    currentLineNumber = 1;
    lastBreak = -2;
  }

  public boolean isPaused() {
    return paused;
  }

  public void setPaused(boolean paused) {
    this.paused = paused;
  }

  public int getCurrentLineNumber() {
    return currentLineNumber;
  }

  public void setCurrentLineNumber(int currentLineNumber) {
    this.currentLineNumber = currentLineNumber;
  }

  public Stack<FunctionEnvironmentRecord> getStackFunctions() {
    Stack<FunctionEnvironmentRecord> tempStack = functionEnvRecordStack;
    return tempStack;
  }

  public void enterRecord(String symbol, int value) {
    this.functionEnvRecordStack.peek().enter(symbol, value);
    return;
  }

  public void setFunctionInfoRecord(String functionName, int startingLineNumber, int endingLineNumber) {
    this.functionEnvRecordStack.peek().setFunctionInfo(functionName, startingLineNumber, endingLineNumber);
    return;
  }

  public void setCurrentLineNumberRecord(int sourceLineNumber) {

    this.functionEnvRecordStack.peek().setCurrentLineNumber(sourceLineNumber);
    return;
  }

  public void startNewFunction() {
    this.functionEnvRecordStack.push(new FunctionEnvironmentRecord());
    this.functionEnvRecordStack.peek().beginScope();

  }

  public void popFunction() {
    if (!functionEnvRecordStack.isEmpty()) {
      this.functionEnvRecordStack.pop();

    }

  }

  public void popVariables(int count) {
    if (!functionEnvRecordStack.isEmpty()) {
      this.functionEnvRecordStack.peek().pop(count);
    }
  }

  public int getValueFromStack(int offsetPosition) {
    return getRunTimeStack().getOffsetValue(offsetPosition);

  }

  @Override
  public void executeProgram() {

    continueOn = true;

    while (continueOn) {

      ByteCode code = super.getProgram().getCode(super.getProgramCounter());
      if (code == null) {
        break;
      }
      
      code.execute(this);
      super.setProgramCounter(getProgramCounter() + 1);

      if(previousLineNumber!=currentLineNumber){
        lastBreak=(-2);
      }

      if (!functionEnvRecordStack.isEmpty()) {

        previousLineNumber = currentLineNumber;

        currentLineNumber = functionEnvRecordStack.peek().getCurrentLine();

        

        if (functionEnvRecordStack.peek().getCurrentLine() != lastBreak) {

          if (breakpoints.contains(functionEnvRecordStack.peek().getCurrentLine())) {

            lastBreak = currentLineNumber;
            break;
          }
        }
      }
      

      
    }

  }

  public void setBreakPoint(int newBreakPoint) {

    breakpoints.add(newBreakPoint);
  }

  public void step() {

    ByteCode code = super.getProgram().getCode(super.getProgramCounter());
    code.execute(this);
    super.setProgramCounter(getProgramCounter() + 1);
    
    if (!functionEnvRecordStack.isEmpty()) {
      currentLineNumber = functionEnvRecordStack.peek().getCurrentLine();

    }
    return;

  }

  public void setContinueOn(boolean flag) {
    continueOn = flag;
  }

  public void exit() {
    super.closeInput();
    exit();
  }

  @Override
  public void lineCodeMethod(LineDebuggerCode lineDbg) {

    if (this.getStackFunctions().isEmpty()) {
      this.startNewFunction();
    }

    this.setCurrentLineNumberRecord(lineDbg.getSourceLineNumber());
    this.setCurrentLineNumber(lineDbg.getSourceLineNumber());

    return;

  }

  @Override
  public void callDebuggerMethod(CallDebuggerCode callDbg) {

    this.pushStackAdresses(this.getProgramCounter() + 1);
    this.setProgramCounter(callDbg.getAddress() - 1);
    callDbg.setArgsNumber(super.getArgsFunction());
    callDbg.initArgs();
    Stack<Integer> storeStack = new Stack<>();
    int arg;
    for (int i = 0; i < callDbg.getArgsNumb(); i++) {
      arg = super.popStack();
      storeStack.push(arg);
    }
    while (!storeStack.isEmpty()) {
      callDbg.addArgs(storeStack.peek());
      super.pushStack(storeStack.pop());
    }
    return;
  }

  @Override
  public void formalDebuggerMethod(FormalDebuggerCode fromalDbg) {
    this.enterRecord(fromalDbg.getFormalName(), fromalDbg.getOffset());
    return;
  }

  @Override
  public void functionDebuggerMethod(FunctionDebuggerCode functionDbg) {
    this.setFunctionInfoRecord(functionDbg.getFunctionName(), functionDbg.getStartLineNumber(),
        functionDbg.getEndLineNumber());
  }

  @Override
  public void popDebuggerMethod(PopDebuggerCode popDbg) {
    for (int i = 0; i < popDbg.getLevelsToPop(); i++) {
      super.popStack();
    }
    this.popVariables(popDbg.getLevelsToPop());
    return;
  }

  @Override
  public void returnDebuggerMethod(ReturnDebuggerCode returnDbg) {
    returnDbg.setReturnedValue(super.peekStack());
    super.popFrame();
    super.setProgramCounter(super.popStackAddresses() - 1);

    this.popFunction();
    return;
  }

  @Override
  public void litDebuggerCode(LitDebuggerCode litDbg) {
    this.pushStack(litDbg.getLiteralValue());
    if (litDbg.getVariableName().length() > 0) {
      int offset = this.getOffestRunTimeStack();
      this.enterRecord(litDbg.getVariableName(), offset);
    }

    return;
  }

}