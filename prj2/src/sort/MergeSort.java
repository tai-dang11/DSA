package sort;

public class MergeSort {
  
   public static void merge(int[] numbers, int i, int j, int k) {
      int mergedSize = k - i + 1; 
      int[] mergedNumbers = new int[mergedSize];                
      int mergePos = 0;                         
      int leftPos = i;                         
      int rightPos = j + 1;                                           
      while (leftPos <= j && rightPos <= k) {
         if (numbers[leftPos] <= numbers[rightPos]) {
            mergedNumbers[mergePos] = numbers[leftPos];
            leftPos++;
         }
         else {
            mergedNumbers[mergePos] = numbers[rightPos];
            rightPos++;
         }
         mergePos++;
      }// end while
      while (leftPos <= j) {
         mergedNumbers[mergePos] = numbers[leftPos];
         leftPos++;
         mergePos++;
      }
      while (rightPos <= k) {
         mergedNumbers[mergePos] = numbers[rightPos];
         rightPos++;
         mergePos++;
      } 
      for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
         numbers[i + mergePos] = mergedNumbers[mergePos];
      }
   }

   public static void mergeSort(int[] numbers, int i, int k) {
      int j = 0;
      if (i < k) {
         j = (i + k) / 2;
         mergeSort(numbers, i, j);
         mergeSort(numbers, j + 1, k);
         merge(numbers, i, j, k);
      }
   }
}