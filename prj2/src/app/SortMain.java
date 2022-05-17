package app;

import java.io.*;

public class SortMain {

  public static void main(String[] args) throws Exception {
  
      DataSourceAccessor<Integer> dataAccessor = new IntegerFileAccessor<Integer>("largedata.dat","sorted.dat");
      BigDataSorter sorter = new BigDataSorter(dataAccessor);
      sorter.sort();
      displayRecords("sorted.dat", 50);
  }
  
  /*  
   *  Diagnostic method: Display the first numRecords in the specified file.
   *  Throws an IOException if the file can't be opened, throws an Exception 
   *  if the file contains less data than numRecords.
   */
  public static void displayRecords(String filename, int numRecords) {
    DataInputStream input = null;
      try {
        input = new DataInputStream(new FileInputStream(filename));
        for (int i = 0; i < numRecords; i++){
          if(input.available()>0)
             System.out.print(input.readInt() + " ");
          else
             throw new Exception("The file contains less then "+numRecords+" records.");
        }
        input.close();
      }
      catch (IOException ioex) {
        ioex.printStackTrace();
      }
      catch (Exception ex) {
        System.out.println(ex);
        ex.printStackTrace();
      }
      finally {
        if (input != null) {
           try{input.close();}
           catch(IOException ioex){/*all important errors ave been caught.*/}
          }
      }
  }
  
  /*  
   *  Diagnostic method: Returns a count of the number of records in the specified file.
   *  Throws an IOException if the file can't be opened.
   */
  public static int getNumRecords(String filename) {
      int numRecords = 0;
      try {
        DataInputStream input = new DataInputStream(new FileInputStream(filename));
        while(input.available()>0){
             input.readInt();
             numRecords++;
        }
        input.close();
      }
      catch (IOException ioex) {
        ioex.printStackTrace();
      }
     return numRecords;
  }
   
}