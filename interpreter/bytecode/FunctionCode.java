package interpreter.bytecode;

import java.util.Formatter;
import java.util.Vector;
import interpreter.VirtualMachine;

public class FunctionCode extends ByteCode{

    private String byteCodeType;
    private String functionName;
    private int startLineNumber;
    private int endLineNumber;

    @Override
    public void execute(VirtualMachine vm) {
       return;
        
    }

    public int getEndLineNumber() {

        int tempEndlineNumber = endLineNumber;
        return tempEndlineNumber;
    }

    public void setEndLineNumber(int endLineNumber) {

        this.endLineNumber = endLineNumber;
    }

    public int getStartLineNumber() {

        int tempLineNumber = startLineNumber;
        return tempLineNumber;
    }

    public void setStartLineNumber(int startLineNumber) {

        this.startLineNumber = startLineNumber;
    }

    public String getFunctionName() {

        String tempName = new String(functionName);
        return tempName;
    }

    public void setFunctionName(String functionName) {

        this.functionName = functionName;
    }

    public String getByteCodeType() {

        String tempType = new String(byteCodeType);
        return tempType;
    }

    @Override
    public void init(Vector<String> args) {
        
        byteCodeType = args.get(0);
        setFunctionName(args.get(1));
        setStartLineNumber(Integer.parseInt(args.get(2)));
        setEndLineNumber(Integer.parseInt(args.get(3)));
         
    }

    public String getClassName(){
        return "interpreter.bytecode.FunctionCode";
    }

    @Override
    public String toString(){
        String column1Format = "%-25.25s";
       
        String formatInfo = column1Format;

        String instructionString = getByteCodeType()+" "+getFunctionName()+" "+getStartLineNumber()+" "+getEndLineNumber();
        
        StringBuilder wholeString = new StringBuilder();
        Formatter fmt = new Formatter(wholeString);
        fmt.format(formatInfo, instructionString);
        String retString = wholeString.toString();
        fmt.close();
        return retString;
    }
    
}
