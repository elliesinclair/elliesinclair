
public class XtoTheN {

	public static void main(String[] args) {
		System.out.println(bruteForce(2, 5));
		System.out.println(decreaseAndConquer(2, 3));
	}

	public static int bruteForce(int x, int n) {
		int result = 1;

		for(int i = 0; i < n; i++) {
			result *= x;
		}
		return result;
	}

	public static int decreaseAndConquer(int x, int n) {
		int result = 1;

		do {
			if(n == 0) {
				return result;
			}
			else if(n%2 == 0) {
				return decreaseAndConquer(x, n/2)*decreaseAndConquer(x, n/2); //(x^(n/2))^2
			}
			else {
				return x*decreaseAndConquer(x, n/2)*decreaseAndConquer(x, n/2);
			}
			
		} while(n != 1);
	}

}
