
public class MergeSort {
	public static void main(String[] args) {
		int[] A = {2, 5, 1, 6, 7, 3, 9, 0};
		mergeSort(A, 0, A.length-1);
		printArray(A);
	}

	public static void merge(int[] A, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1];
		int[] R = new int[n2];

		int i;
		int j;

		for(i = 0; i < n1; i++) {
			L[i] = A[p + i]; 
		}

		for(j = 0; j < n2; j++) {
			R[j] = A[q + j + 1];
		}

		i = 0;
		j = 0;

		for(int k = p; k <= r; k++) {

			if(i < n1 && j < n2) { 
				if( L[i] <= R[j]) {
					A[k] = L[i];
					i++;
				}

				else { //else if(j < n2 && A[k] == R[j]) 
					A[k] = R[j];
					j++; 
				}
			}
			
			else {
				if(i >= n1) {
					A[k] = R[j];
					j++;
				}
				else {
					A[k] = L[i];
					i++;
				}
			}
		}
	}

	public static void mergeSort(int[] A, int p, int r ) {
		if(p >= r) {
			return;
		}
		else {
			int q = (p+r)/2;
			mergeSort(A, p, q);
			mergeSort(A, q+1, r);
			merge(A, p, q, r);
		}
	}

	public static void printArray(int[] A) {
		String temp = "";
		for(int i = 0; i < A.length; i++) {
			temp += A[i] + " ";
		}
		System.out.println(temp);
	}
}
