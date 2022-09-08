package interpreter.debugger;

public class Entry {

    private int lineNumber;
    private String sourceLine;
    private Boolean isBreakpointLine;

    public Entry(int lineNumber, String sourceLine, Boolean isBreakpointLine) {
        this.lineNumber = lineNumber;
        this.sourceLine = sourceLine;
        this.isBreakpointLine = isBreakpointLine;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public Boolean getIsBreakpointLine() {
        return isBreakpointLine;
    }

    public void setIsBreakpointLine(Boolean isBreakpointLine) {
        this.isBreakpointLine = isBreakpointLine;
    }

    public String getSourceLine() {
        String tempSourceLine = new String(sourceLine);
        return tempSourceLine;
    }

    public void setSourceLine(String sourceLine) {
        this.sourceLine = sourceLine;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    
}
