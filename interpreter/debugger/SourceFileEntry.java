package interpreter.debugger;

import java.util.*;

public class SourceFileEntry {

    private Vector<Entry> sourceLineArray;
    private int currentExecution;

    public SourceFileEntry() {
        sourceLineArray = new Vector<>();
        currentExecution = 1;
    }

    public int getCurrentExecution() {
        return currentExecution;
    }

    public void setCurrentExecution(int currentExecution) {
        this.currentExecution = currentExecution;
    }

    public Vector<Entry> getEntriesArray() {
        Vector<Entry> tempArray = sourceLineArray;
        return tempArray;
    }

    public void add(Entry newEntry) {
        sourceLineArray.add(newEntry);
        return;
    }

    public String buildALine(Entry entry){

        StringBuilder sourceLineString = new StringBuilder();

        String column1Format = "%-3s%-3s%s";

        String formatInfo = column1Format;

        String breakPoint, currentExecutionString, linecode;

        Formatter fmt = new Formatter(sourceLineString);

        if (entry.getLineNumber() == currentExecution) {
            currentExecutionString = "->";
        } else {
            currentExecutionString = "";
        }
        if (entry.getIsBreakpointLine()) {
            breakPoint = "*";
        } else {
            breakPoint = "";
        }
        linecode = (entry.getLineNumber()) + ": ";
        linecode += entry.getSourceLine() + "\n";

        fmt.format(formatInfo, currentExecutionString, breakPoint, linecode);

        fmt.close();
        return sourceLineString.toString();

    }
    

    public String getSingleLine(int lineNumber) {

        int counter = 0;
        for (Entry entry : sourceLineArray) {
            counter++;
            if (counter == lineNumber) {

                String aLine = buildALine(entry);
                return aLine;
            }
        }
        return null;

    }

    @Override
    public String toString() {
        
        StringBuilder sourceCodeString = new StringBuilder();
        ListIterator<Entry> entryIterator = sourceLineArray.listIterator();
        
        while (entryIterator.hasNext()) {

            String aLine = buildALine(entryIterator.next()) ;
            sourceCodeString.append(aLine);
            
        }

        return sourceCodeString.toString();

    }

    public void setBreakPoint(int lineNumber) {
        sourceLineArray.get(lineNumber-1).setIsBreakpointLine(true);
    }

}
