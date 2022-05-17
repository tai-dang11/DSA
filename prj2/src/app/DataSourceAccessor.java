package app;

/*
 * This interface supprts access to data sources that are not able to be sorted in memory.
 * It allows access to an "original" data source and a sorted data source, which stores the sorted result.
 * It also allows for the use of temporary data sources, which can be used to store 
 * and retrieve data as needed during the sort. The initialization and opening of the original and sorted 
 * data sources is left to the implementing classes.
 */
 
 public interface DataSourceAccessor<T> {

   // Returns true if more data can be read from the original data source, false otherwise.
   public boolean origSrcHasNext()throws Exception;
   
   // Returns the next data record from the original source. Throws an exception if there is no
   // more data to be read. Note that this data source will be closed after the last record has
   // been read and cannot be re-opened.
   public T origSrcGetNext()throws Exception;
   
   // Open the final, sorted data file for write.
   public void openSortedForWrite()throws Exception;
    
   // This data source is open for writing by default. This method writes a record 
   // to the final, sorted data set. It cannot be re-opened.
   public void sortedWriteNext(T item)throws Exception;
   
   // Closes this data source.
   public void sortedDataClose()throws Exception;
   
   // Data source(s) used for temporary storage. Use the argument to 
   // uniquely identify the data source. 
   
   public void openTempDSforRead(String dsIdentifier)throws Exception;

   public boolean tempDShasNext(String dsIdentifier)throws Exception;
   
   public T tempDSgetNext(String dsIdentifier)throws Exception;
   
   public void openTempDSforWrite(String dsIdentifier)throws Exception;
   
   public void tempDSwriteNext(String dsIdentifier, T item)throws Exception;
   
   public void tempDSclose(String dsIdentifier)throws Exception;
      
}