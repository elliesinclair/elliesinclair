
public class InsertionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {4, 1, 5, 8, 2};
		
		insertionSort(A);
		printArray(A);
	}
	
	public static int[] insertionSort(int[] A) {
		for(int j = 1; j < A.length; j++) {
			int key = A[j]; 
			int i = j - 1;
			
			while(i >= 0 && A[i] > key) {
				A[i+1] = A[i];
				i = i - 1;
			}
			A[i+1] = key;
		}
		return A;
	}
	
	public static void printArray(int[] A) {
		String temp = "";
		for(int i = 0; i < A.length; i++) {
			temp += A[i] + " ";
		}
		System.out.println(temp);
	}

}
