package app;

import java.io.*;

/*
 * This class implements a DataSourceAccessor for binary Integer files.
 * It uses DataInputStream and DataOutputStream objects to access the files.
 * 
 */
@SuppressWarnings("unchecked")
public class IntegerFileAccessor<T> implements DataSourceAccessor<T> {

   private FileStreamObject origFileStream = null;
   private FileStreamObject sortedFileStream = null; 
   private String resultFileName;
   // manages up to 5 temporary data sources
   private static final int MAX_TEMP_SOURCES = 5;
   private FileStreamObject[] dataSources;
   private int numSources = 0;
   private int MAX_DATA;
   private int curData = 0;

   public IntegerFileAccessor(String srcFileName, String sortedFileName) throws Exception {
      resultFileName = sortedFileName; 
      origFileStream = new FileStreamObject(srcFileName, false);  
      dataSources = new IntegerFileAccessor.FileStreamObject[MAX_TEMP_SOURCES];
      MAX_DATA = BigDataSorter.MAX_ARRAY_SIZE;
   }
   
   // Read from the original data source:
   // returns true if there is more data to read, false otherwise.
   public boolean origSrcHasNext()throws Exception {
      if(origFileStream.isClosed())
         throw new Exception("The original source is closed.");
      return origFileStream.hasNext();
   }
    
   // read the next integer
   public T origSrcGetNext()throws Exception{
      T data = null;
      if(origFileStream.isClosed())
         throw new Exception("The original source is closed.");
      if(!origFileStream.hasNext()){
        origFileStream.close();
      }
      else {
        curData++;
        if(curData > MAX_DATA)
           throw new Exception("You are exceeding the in-memory data limit of "+MAX_DATA+".");
        data = origFileStream.getNext();
      }
      return data;
   }
   
   public void origSrcClose()throws Exception {
      if(!origFileStream.isClosed())
         origFileStream.close();
   }
   
   public void openSortedForWrite()throws Exception{
      sortedFileStream = new FileStreamObject(resultFileName, true);
   }

   
   public void sortedWriteNext(T item)throws Exception{
      if(sortedFileStream.isClosed())
         throw new Exception("The sorted data source is closed.");
       sortedFileStream.writeNext(item);
       curData--;
   }
   
   public void sortedDataClose()throws Exception {
      if(sortedFileStream.isClosed())
         throw new Exception("The sorted data source is closed.");
      sortedFileStream.close();
   }
   
   // Data source(s) used for temporary storage. Use the argument to 
   // uniquely identify the data source. 
   
   public void openTempDSforRead(String fileName)throws Exception {
      if(numSources>=MAX_TEMP_SOURCES)
         throw new Exception("Only 5 temporary sources are supported.");
      FileStreamObject obj = getSource(fileName);
      if(obj!=null){
         if(obj.isClosed())
            obj.open(false);
         else if (obj.isWrite())
            throw new Exception("Data source: "+fileName+" is already open for write. Close it before opening for read.");      
      }
      else{
         dataSources[numSources] = new FileStreamObject(fileName, false);
         numSources++;
      }
   }

   public boolean tempDShasNext(String fileName)throws Exception {
      FileStreamObject obj = getSource(fileName);
      if(obj==null || obj.isClosed())
         throw new Exception("Data source: "+fileName+" has not been opened.");
      return obj.hasNext(); 
   }
   
   public T tempDSgetNext(String fileName)throws Exception {
      FileStreamObject obj = getSource(fileName);
      if(obj==null || obj.isClosed())
         throw new Exception("Data source: "+fileName+" has not been opened.");
      if(obj.isWrite())
         throw new Exception("Data source: "+fileName+" is already open for write. Close it before opening for read.");
      curData++;
      if(curData > MAX_DATA)
           throw new Exception("You are exceeding the in-memory data limit of "+MAX_DATA+".");
      return obj.getNext();
   }
   
   public void openTempDSforWrite(String fileName)throws Exception{
      if(numSources>=MAX_TEMP_SOURCES)
         throw new Exception("Only 5 temporary sources are supported.");
      FileStreamObject obj = getSource(fileName);
      if(obj!=null){
         if(obj.isClosed())
           obj.open(true);
         else if (!obj.isWrite())
             throw new Exception("Data source: "+fileName+" is already open for read. Close it before opening for write.");
      } 
      else{     
         dataSources[numSources] = new FileStreamObject(fileName, true);
         numSources++;
      }
   }
   
   public void tempDSwriteNext(String fileName, T item)throws Exception{
      FileStreamObject obj = getSource(fileName);
      if(obj==null || obj.isClosed())
         throw new Exception("Data source: "+fileName+" has not been opened.");
      if(!obj.isWrite())
         throw new Exception("Data source: "+fileName+" is already open for read. Close it before opening for write.");
       obj.writeNext(item);
       curData--;   
   }
   
   public void tempDSclose(String fileName)throws Exception{
      FileStreamObject obj = getSource(fileName);
      if(obj==null)
         throw new Exception("Data source: "+fileName+" has not been opened.");
      obj.close();   
   }
   
   // Returns null if not found.
   private FileStreamObject getSource(String fileName){
      FileStreamObject obj = null;
      for(FileStreamObject curObj : dataSources){
        if (curObj!=null && curObj.isFile(fileName))
           obj = curObj;
      }
      return obj;
   }
   
   // private class to encapsulate file streams
   private class FileStreamObject {
      static final int BUFFER_SIZE = 100000; 
      String file = null;
      boolean isWrite = false;
      boolean isClosed = false;
      DataInputStream instream = null;
      DataOutputStream outstream = null;
       
     public FileStreamObject(String fileName, boolean forWrite) throws Exception {
         file = fileName;
         isWrite = forWrite;
         if (isWrite)
           outstream = new DataOutputStream(
               new BufferedOutputStream(new FileOutputStream(fileName), BUFFER_SIZE));
         else
           instream = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(fileName), BUFFER_SIZE));
      }
      
     public boolean isFile(String fileName){return (file.equals(fileName));}
      
     public boolean isWrite(){return isWrite;} 
     public boolean isClosed(){return isClosed;} 
     
     public boolean hasNext()throws Exception {
        boolean hasMore = false;
        if (isWrite)
           throw new Exception("This is a write-only source");
        else
           hasMore = instream.available()>0;
        return hasMore;     
     }
      
     public T getNext()throws Exception{
        if(isWrite)
           throw new Exception("This is a write-only source");
        Integer data = null;
        if(instream.available()<=0){
           instream.close();
           throw new Exception("No more to read");
        }
        else{
           data = Integer.valueOf(instream.readInt());
        }
        return (T)data;
      }
      
      public void writeNext(T item)throws Exception{
         if(!isWrite)
           throw new Exception("This is a read-only source");
         Integer dataInt = (Integer)item;
         outstream.writeInt(dataInt.intValue());
      }
      
      public void open(boolean forWrite)throws Exception {
         if (forWrite){
           outstream = new DataOutputStream(
               new BufferedOutputStream(new FileOutputStream(file)));
           isWrite = true;
         }
         else {
           instream = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(file)));
           isWrite = false;
        }
        isClosed = false;
      }
      
      public void close()throws Exception {
         if(!isClosed){
            if (isWrite){
              outstream.close();
              outstream = null;
              isClosed = true;
            }
            else {
              instream.close();
              instream=null;
              isClosed = true;
           }
        }
      }

   }// end private class

}