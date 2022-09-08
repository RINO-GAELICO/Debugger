package interpreter.debugger;

import java.io.*;


public class SourceCodeLoader {

  private String nextLine;
  private BufferedReader source;
  private SourceFileEntry sourceFileEntries;
  private int lineCount;

  public SourceCodeLoader(String byteCodeFile) throws IOException{

    sourceFileEntries = new SourceFileEntry();
    lineCount = 1;

    StringBuilder filename = new StringBuilder(byteCodeFile);
    filename.append(".x");
    source = new BufferedReader(new FileReader(filename.toString()));


  }

  public SourceFileEntry loadCode() throws IOException{

    nextLine = source.readLine();
    

    if (nextLine == null) {
      throw new IOException();
    }

    while (nextLine!= null) {

      sourceFileEntries.add(new Entry(lineCount, nextLine, false));
      
      nextLine = source.readLine();
      
      lineCount++;
    }

    return sourceFileEntries;

  }
    
    

  

  

  
}

