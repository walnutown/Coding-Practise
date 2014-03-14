package ch11_sorting_searching;

public class CountInversionsInArray {

   /**
    * Let A[1..n] be an array of n distinct numbers. If i<j and A[i] > A[j], then the pair(i, j) is
    * called an inversion of A
    */
   // reference:
   // http://www.geeksforgeeks.org/counting-inversions/
   // CLSR, Chapter2

   // we can modify merge sort to solve this problem
   // each inversion implies one merge-inversion in the merge step.
   // If l<r, no merge; if l>r, we need a merge-inversion, and the number of inversions is mid-l+1
   // (why? because r will be moved to the position before l, there're mid-l+1 merge-inversions
   // here)

   public static int mergeSort(int[] arr, int start, int end) {
      if (start >= end)
         return 0; // notice >= here, because in 'mergeSort(arr,start,mid)', 'mid' keep the same. if
                   // start>end, may cause dead loop
      int count = 0;
      int mid = (start + end) >> 1;
      count += mergeSort(arr, start, mid);
      count += mergeSort(arr, mid + 1, end);
      count += merge(arr, mid, start, end);
      return count;
   }

   public static int merge(int[] arr, int mid, int start, int end) {
      int[] helper = new int[arr.length];
      int count = 0;
      for (int i = start; i <= end; i++)
         helper[i] = arr[i];
      int i = start, j = mid + 1, index = start;
      while (i <= mid && j <= end) {
         if (helper[i] < helper[j])
            arr[index++] = helper[i++];
         else {
            arr[index++] = helper[j++];
            count += mid - i + 1;      // key step here
         }
      }
      while (i <= mid) {
         arr[index++] = helper[i++];
      }
      while (j <= end) {
         arr[index++] = helper[j++];
      }
      return count;
   }

   public static void main(String[] args) {
      int[] A = new int[] { 2, 3, 8, 6, 1 };
      int count = mergeSort(A, 0, A.length - 1);
      System.out.println(count);

   }

}
