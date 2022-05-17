package test;
import java.io.*;
import app.*;
/* this file contains six tests to perform on BigDataSorter. */
public class TestBigDataSorter {

        private BigDataSorter sorter;
        private DataSourceAccessor<Integer> dataAccessor;
        private String dataSourceFile = "largedata.dat";
        private String sortedFile = "sorted.dat";
        private String tempFile1 = "temp1.dat";
        private String tempFile2 = "temp2.dat";
        private String tempFile3 = "temp3.dat";
        boolean exceptionThrown = false;
        private int segmentSize = 43;
        private int numSegments = 12;
        
    public static void main(String[] args)throws Exception {

        TestBigDataSorter tester = new TestBigDataSorter();
        // System.out.println(tester.initSegmentsTest1());
        // System.out.println(tester.initSegmentsTest2());
        // System.out.println(tester.copyHalfFromF1ToF2Test());
        // System.out.println(tester.mergeTwoSegmentsTest());
        // System.out.println(tester.resultFileIsSortedTest());
        System.out.println(tester.sortedFileCorrectTest());

    }

    public boolean initSegmentsTest1() throws Exception {
        init();
        boolean result = false;
        int numSegments = 0;
        try{
         numSegments = sorter.initSegments (segmentSize);
        } catch(Exception ex) {
            System.out.println(ex);
            finish();
            return false;
        }
        if(numSegments==12){
            System.out.println("The number of segments "+numSegments+" is correct");
            result = true;
        }
        else{
            System.out.println("The number of segments "+numSegments+" is incorrect");
            result = false;
        }
        finish();
        return result; 
    }

    public boolean initSegmentsTest2()  throws Exception {  
        init();
        String correctFile = "testFiles/temp1_test_initSegments_correct.dat";
        boolean res = true;
        try {
            sorter.initSegments(segmentSize);
               
            DataInputStream inputActual = new DataInputStream(new FileInputStream(tempFile1));
            DataInputStream inputCorrect = new DataInputStream(new FileInputStream(correctFile));
             
             // Test that the file temp1.dat has no data
            if(inputActual.available() <= 0){
                 System.out.println("The file temp1.dat has no data.");
                 inputActual.close();
                 inputCorrect.close();
                 finish();
                 return false;
             }
            int testInt, correctInt = 0;
            while (inputActual.available() > 0){
               testInt = inputActual.readInt();
               correctInt = inputCorrect.readInt();
               res = res && (testInt == correctInt);
            }
             // test for remaining data in correct file
            if(inputCorrect.available()>0){
                System.out.println("The temp1.dat file contains less data that the correct file.");
                inputActual.close();
                inputCorrect.close();
                finish(); 
                return false;
            } 
             inputActual.close();
             inputCorrect.close();
        } catch (Exception ex) {
             System.out.println(ex);
             finish();
             return false; 
        }
        if(res) {
          System.out.println("The data has been sorted in segments and that data was written to temp1.dat.");
        } else {
           System.out.println("The data has not been successfully sorted in segments and that data was written to temp1.dat.");
        }
        finish();
        return res;
    } 
       
    public boolean copyHalfFromF1ToF2Test()throws Exception {  
        init();
        String correctFile = "testFiles/temp2_test_copy_correct.dat";
        boolean res = true;
        try {
            sorter.initSegments (segmentSize);
            dataAccessor.openTempDSforRead(tempFile1);
            dataAccessor.openTempDSforWrite(tempFile2);
            sorter.copyHalfFromF1ToF2(numSegments, segmentSize, tempFile1, tempFile2);
            dataAccessor.tempDSclose(tempFile2);
             
            DataInputStream inputActual = new DataInputStream(new FileInputStream(tempFile2));
            DataInputStream inputCorrect = new DataInputStream(new FileInputStream(correctFile));
             
            // Test that the file temp2.dat has no data
            if(inputActual.available() <= 0){
                System.out.println("The file temp2.dat has no data."); 
                finish();
                inputActual.close();
                inputCorrect.close();
                return false;
            }
              
            int testInt, correctInt = 0;
            while (inputActual.available() > 0){
               testInt = inputActual.readInt();
               correctInt = inputCorrect.readInt();
               res = res && (testInt == correctInt);
            }
             // test for remaining data in correct file
            if(inputCorrect.available()>0){
                System.out.println("The temp2.dat file contains less data that the correct file.");
                finish();
                inputActual.close();
                inputCorrect.close();
                return false;
            }  
            inputActual.close();
            inputCorrect.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
            finish();
            return false;
        }
        if(res)
           System.out.println("The correct data was written to temp2.dat.");
        else
           System.out.println("The correct data was not written to temp2.dat.");
        finish();
        return res;
    }  
        
    public boolean mergeTwoSegmentsTest()throws Exception  { 
        init(); 
        String correctFile = "testFiles/temp3_test_merge2segments_correct.dat";
        boolean res = true;
        int num = 0;
        try {
            sorter.initSegments (segmentSize);
            dataAccessor.openTempDSforRead(tempFile1);
            dataAccessor.openTempDSforWrite(tempFile2);
            sorter.copyHalfFromF1ToF2(numSegments, segmentSize, tempFile1, tempFile2);
            dataAccessor.tempDSclose(tempFile2);
            dataAccessor.openTempDSforRead(tempFile2);
            dataAccessor.openTempDSforWrite(tempFile3);
            sorter.mergeTwoSegments(segmentSize, tempFile1, tempFile2, tempFile3);
            dataAccessor.tempDSclose(tempFile3);
              
            DataInputStream inputActual = new DataInputStream(new FileInputStream(tempFile3));
            DataInputStream inputCorrect = new DataInputStream(new FileInputStream(correctFile));            
            if(inputActual.available() <= 0){
               System.out.println("The file temp3.dat has no data.");
               finish();
               inputActual.close();
               inputCorrect.close();
               return false;
            }
            int testInt, correctInt = 0;
            
            while (inputActual.available() > 0){
                num++;
               testInt = inputActual.readInt();
               correctInt = inputCorrect.readInt();

               res = res && (testInt == correctInt);
               System.out.println(testInt + ", "+ correctInt +": " + res);
               System.out.println(num);
            }
             // test for remaining data in correct file
            if(inputCorrect.available()>0){
                System.out.println("The temp3.dat file contains less data that the correct file.");
                finish();
                inputActual.close();
                inputCorrect.close();
                return false;
            }              
            inputActual.close();
            inputCorrect.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
            finish();
            return false;
        }
        if(res){
            System.out.println("The first two segments were correctly merged and data was written to temp3.dat.");
        } else{
            System.out.println("The first two segments were not correctly merged or data was not written to temp3.dat.");    
        }
        finish();
        return res;   
    } 
           
    public boolean resultFileIsSortedTest()throws Exception  { 
        init();  
        boolean res = true;
        try {
            sorter.sort();
            DataInputStream input = new DataInputStream(new FileInputStream(sortedFile));
            int prevInt = input.readInt();
            int curInt = 0;
            while (input.available() > 0){
               curInt = input.readInt();
               res = res && (prevInt <= curInt);
               System.out.println(curInt + ", " + prevInt);
               
               prevInt = curInt;
               
            }
            input.close();
        }catch (Exception ex) {
            System.out.println(ex);
            finish();
            return false;
        } 
        if(res){
            System.out.println("The result file data is sorted in ascending order.");
        } else{
            System.out.println("The result file data is not sorted in ascending order.");
        }
        finish();
        return res;
    }  
   
    public boolean sortedFileCorrectTest() throws Exception  {
        init();
        boolean res = false;
        try{
            sorter.sort();
        } catch(Exception ex) {
            System.out.println(ex);
            finish();
            return false;
        }  
        DataInputStream instreamCorrect, instreamStudent = null;  
        boolean correctLength = true;
        boolean correctOrder = true;
        int correctVal, studentVal = 0; 
        try{
            instreamStudent = new DataInputStream(
                             new BufferedInputStream(new FileInputStream(sortedFile)));
            instreamCorrect = new DataInputStream(
                             new BufferedInputStream(new FileInputStream("testFiles/sorted_correct.dat")));                
            int num=0;        
            while(instreamCorrect.available()>0){ 
                if(instreamStudent.available()<=0) {
                    correctLength = false;
                    System.out.println("length mismatch at "+num);
                    break;   
                }
                num++;        
                correctVal = instreamCorrect.readInt();
                studentVal = instreamStudent.readInt();
                System.out.println(correctVal +": " + studentVal);
                System.out.println(num);
                // if(correctVal == studentVal){
                //     System.out.println(studentVal + ", "+correctVal); //Put breakpoint on this line
                //     System.out.println(num);
                // }
                correctOrder = correctOrder && (correctVal==studentVal);  
            }          
            instreamStudent.close();     
            instreamCorrect.close();
        } catch(Exception ex){
            System.out.println(ex);
            finish();
            return false;
        }
        if(correctLength){
            System.out.println("The sorted.dat file contains all of the data.");
            res = true;
        } else{
            System.out.println("The sorted.dat file does not contain all of the data.");
            res = false;
        }
        if(correctOrder) {
            System.out.println("The sorted.dat file contains all data correctly sorted in ascending order.");
            res = res && true;
        } 
        else{
            System.out.println("The sorted.dat file data is not correctly sorted in ascending order.");
            res = false;
        }
        finish();
        return res;
    }

    private void init() throws Exception {
        dataAccessor = new IntegerFileAccessor<Integer>(dataSourceFile, sortedFile);
        sorter = new BigDataSorter(dataAccessor);
    }

    private void finish(){
        dataAccessor = null;
        sorter = null;    
    }
}
