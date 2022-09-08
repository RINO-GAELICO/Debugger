package interpreter.bytecode;


import java.util.Formatter;
import java.util.Vector;
import interpreter.VirtualMachine;

public class LineCode extends ByteCode {

    private String byteCodeType;
    private int sourceLineNumber;

    @Override
    public void execute(VirtualMachine vm) {
        // TODO Auto-generated method stub
        
    }

    public String getByteCodeType() {
        String tempType = new String(byteCodeType);
        return tempType;
    }

    public int getSourceLineNumber() {
        int tempLineNumber = sourceLineNumber;
        return tempLineNumber;
    }

    public void setSourceLineNumber(int sourceLineNumber) {
        this.sourceLineNumber = sourceLineNumber;
    }

    @Override
    public void init(Vector<String> args) {
        byteCodeType = args.get(0);
        setSourceLineNumber(Integer.parseInt(args.get(1)));
        
    }

    public String getClassName(){
        return "interpreter.bytecode.LineCode";
    }

    @Override
    public String toString(){
        String column1Format = "%-25.25s";
        
        String formatInfo = column1Format;

        String instructionString = getByteCodeType()+" "+getSourceLineNumber();
        
        StringBuilder wholeString = new StringBuilder();
        Formatter fmt = new Formatter(wholeString);
        fmt.format(formatInfo, instructionString);
        String retString = wholeString.toString();
        fmt.close();
        return retString;
    }
    
}
