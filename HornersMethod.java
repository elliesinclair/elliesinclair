
public class HornersMethod {

	public static void main(String[] args) {
		int x = 3;
		int[] coeff = {1, 2, 3};
		
		System.out.println(hornersMethod(coeff, x));
	}

	public static int hornersMethod(int[] coefficients, int x) {
		int n = coefficients.length; //n gets 3
		int result = coefficients[0];
		
		for(int i = 1; i < n; i++) { 
			result *= x;
			result += coefficients[i];
		}
		return result; 
	}
}
