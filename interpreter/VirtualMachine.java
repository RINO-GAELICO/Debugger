package interpreter;

import java.util.Scanner;
import java.util.Stack;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.debuggercodes.CallDebuggerCode;
import interpreter.bytecode.debuggercodes.FormalDebuggerCode;
import interpreter.bytecode.debuggercodes.FunctionDebuggerCode;
import interpreter.bytecode.debuggercodes.LineDebuggerCode;
import interpreter.bytecode.debuggercodes.LitDebuggerCode;
import interpreter.bytecode.debuggercodes.PopDebuggerCode;
import interpreter.bytecode.debuggercodes.ReturnDebuggerCode;

public class VirtualMachine {

  private int pc, argsFunction;
  private RunTimeStack runTimeStack;
  private Stack<Integer> returnAddresses;
  private boolean isRunning;
  private boolean dumpState, dumpONFound,dumpOffFound;
  private Program program;
  private Scanner input;  

  public VirtualMachine(Program program) {
    this.program = program;
    runTimeStack = new RunTimeStack();
    returnAddresses = new Stack<>();
    input = new Scanner(System.in);
    dumpState = false;
    dumpONFound = false;
    dumpOffFound = false;
  }

  public Scanner getInput() {
    return input;
  }

  public int getArgsFunction() {
    return argsFunction;
  }

  public void setArgsFunction(int argsFunction) {
    this.argsFunction = argsFunction;
  }

  public int popStackAddresses() {
    return returnAddresses.pop().intValue();
  }

  public void pushStackAdresses(Integer newAddress){
    returnAddresses.push(newAddress);
    return;
  }

  public Program getProgram(){
    
    return this.program;
    
  }

  public void executeProgram() {
    pc = 0;
    isRunning = true;

    while (isRunning) {
      ByteCode code = program.getCode(pc);
      if (code == null) {
        break;
      }

      code.execute(this);

      if(dumpOffFound){
        dumpState = false;
        dumpOffFound = false;
      }

      if(dumpState){

        System.out.println(code.toString());
        runTimeStack.dump(); 
      }

      if(dumpONFound){
        dumpState = true;
        dumpONFound = false;
      }

      
      pc++;
    }
    input.close();
  }

  public void setIsRunning(boolean newState) {

    isRunning = newState;
  }

  public void setDumpON(boolean newState){

    dumpONFound = newState;
  }

  public void setDumpOFF(boolean newState){
    
    dumpOffFound = newState;
  }

  public int popStack() {

    return runTimeStack.pop();
  }

  public void setProgramCounter(int jump) {

    this.pc = jump;
  }

  public int storeStack(int offsetStore) {

    return runTimeStack.store(offsetStore);
  }

  public int loadStack(int offsetLoad) {

    return runTimeStack.load(offsetLoad);
  }

  public int pushStack(int pushedValue) {

    return runTimeStack.push(pushedValue);
  }

  public void newFrameAt(int newFrameOffset){

    runTimeStack.newFrameAt(newFrameOffset);
  }

  public void popFrame(){

    runTimeStack.popFrame();
  }

  public int peekStack(){

    return runTimeStack.peek();
  }

  public int getProgramCounter(){
    int tempPc = this.pc;
    return tempPc;
  }

  protected RunTimeStack getRunTimeStack(){
    RunTimeStack tempStack = runTimeStack;
    return tempStack;
  }

  public int getOffestRunTimeStack(){
    return runTimeStack.getOffsetTracker();
  }

  public void closeInput(){
    input.close();
  }

  public void lineCodeMethod(LineDebuggerCode lineDbg){
    
  }

  public void callDebuggerMethod(CallDebuggerCode callDbg) {}

  public void formalDebuggerMethod(FormalDebuggerCode fromalDbg){

  }

  public void functionDebuggerMethod(FunctionDebuggerCode functionDbg){

  }

  public void popDebuggerMethod(PopDebuggerCode popDbg){

  }

  public void returnDebuggerMethod(ReturnDebuggerCode returnDbg){

  }
  
  public void litDebuggerCode(LitDebuggerCode litDbg){

  }
}
