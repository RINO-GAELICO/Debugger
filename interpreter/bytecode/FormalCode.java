package interpreter.bytecode;

import java.util.Formatter;
import java.util.Vector;
import interpreter.VirtualMachine;

public class FormalCode extends ByteCode{

    private String byteCodeType;
    private String formalName;
    private int offset;

    @Override
    public void execute(VirtualMachine vm) {
    
        return;
        
    }

    public int getOffset() {
        int tempOffset = offset;
        return tempOffset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getFormalName() {

        String tempName = new String(formalName);
        return tempName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    public String getByteCodeType() {
        
        String tempType = new String(byteCodeType);
        return tempType;
    }


    @Override
    public void init(Vector<String> args) {

        byteCodeType = args.get(0);
        setFormalName(args.get(1));
        setOffset(Integer.parseInt(args.get(2)));

    }

    public String getClassName(){
        return "interpreter.bytecode.FormalCode";
    }

    @Override
    public String toString(){
        String column1Format = "%-25.25s";
       
        String formatInfo = column1Format;

        String instructionString = getByteCodeType()+" "+getFormalName()+" "+getOffset();
        
        StringBuilder wholeString = new StringBuilder();
        Formatter fmt = new Formatter(wholeString);
        fmt.format(formatInfo, instructionString);
        String retString = wholeString.toString();
        fmt.close();
        return retString;
    }
    
}
