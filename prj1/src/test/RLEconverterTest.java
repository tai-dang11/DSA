package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import app.*;

public class RLEconverterTest {
   RLEconverter conv;
   
   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
     conv = new  RLEconverter ();  
   }

/*  Test compression methods **/

   @Test 
   public void compressLineTest1()  {
      char[] fileChars = {'0','1'};
      String line = "000000";
      String correctRes = "6";
      String actualRes = conv.compressLine(line, fileChars);
      assertEquals("Test 1: Test the compressLine method.", correctRes, actualRes);
   }
   
   @Test 
   public void compressLineTest2() {
      char[] fileChars = {'0','1'};
      String line = "11111111";
      String correctRes = "0,8";
      String actualRes = conv.compressLine(line, fileChars);
      assertEquals("Test 2: Test the compressLine method.", correctRes, actualRes);
   }
   
   @Test 
   public void compressLineTest3() {
      char[] fileChars = {'0','1'};
      String line = "1111111111000";
      String correctRes = "0,10,3";
      String actualRes = conv.compressLine(line, fileChars);
      assertEquals("Test 3: Test the compressLine method.", correctRes, actualRes);
   }
   
   @Test 
   public void compressLineTest4() {
      char[] fileChars = {'1','0'};
      String line = "1111111111000";
      String correctRes = "10,3";
      String actualRes = conv.compressLine(line, fileChars);
      assertEquals("Test 4: Test the compressLine method.", correctRes, actualRes);
   }

   /**** decompress tests ********/
   
   @Test  
   public void decompressLineTest1()  {
      char[] fileChars = {'0','1'};
      String line = "8";
      String correctRes = "00000000";
      String actualRes = conv.decompressLine(line, fileChars);
      assertEquals("Test 5: Test the decompressLine method.", correctRes, actualRes);
   }
   
   @Test 
   public void decompressLineTest2() {
      char[] fileChars = {'0','1'};
      String line =  "0,10";
      String correctRes = "1111111111";
      String actualRes = conv.decompressLine(line, fileChars);
      assertEquals("Test 6: Test the decompressLine method.", correctRes, actualRes);
   }
   
   @Test 
   public void decompressLineTest3() {
      char[] fileChars = {'0','1'};
      String line = "0,9,3";
      String correctRes = "111111111000";
      String actualRes = conv.decompressLine(line, fileChars);
      assertEquals("Test 7: Test the decompressLine method.", correctRes, actualRes);
   }
   
   @Test 
   public void decompressLineTest4() {
      char[] fileChars = {'1','0'};
      String line = "9,4";
      String correctRes = "1111111110000";
      String actualRes = conv.decompressLine(line, fileChars);
      assertEquals("Test 8: Test the decompressLine method.", correctRes, actualRes);
   }  
   
}