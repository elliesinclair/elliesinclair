
public class Polynomials {

	public static void main(String[] args) {
		int x = 4; //test variable for x
		int coefficients[] = {1, 2, 3, 4, 5}; //test array for coefficients
		
		
		System.out.println(naiveMethod(coefficients, x));
		System.out.println(hornersMethod(coefficients, x));

	}
	
	public static int naiveMethod(int[] coefficients, int x) {
		int result = 0; //result to store the current value of the polynomial
		int temp = coefficients.length - 1; //this is the starting value of the exponents
		
		for(int i = 0; i < coefficients.length; i++) {
			
			int power = 1; //this variable stores the value of x^n, x^n-1, ..., x^0

			for(int j = 0; j < temp; j++) {
				power *= x;
			}
			
			temp--; //subtract 1 from temp so the exponent decreases 

			result += coefficients[i] * power; //the coefficient at i gets multiplied by the x^n value, and then added to result
		}
		
		return result;
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
