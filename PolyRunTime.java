
public class PolyRunTime {

	public static void main(String[] args) {
		System.out.println("Naive Method:");
		System.out.println("N          Runtime");
		
		for(int j = 5; j < 1000; j+= 5) {
			int x = (int) (Math.random()*11-1);
			
			int[] coefficients = new int[j];
			
			for(int k = 0; k < j; k++) {
				coefficients[k] = (int) (Math.random()*11-1);
			}

			long startTime = System.currentTimeMillis();

			for(int i = 0; i < 1000; i++) {
				naiveMethod(coefficients, x);
			}

			long endTime = System.currentTimeMillis();

			double runTime = (endTime - startTime) / 1000.0;

			System.out.println(j + "          " + runTime);
		}
		
		System.out.println();
		System.out.println("Horner's Method:");
		System.out.println("N          Runtime");
		
		for(int j = 5; j < 1000; j+= 5) {
			
			
			int x = (int) (Math.random()*11-1);
			
			int[] coefficients = new int[j];
			
			for(int k = 0; k < j; k++) {
				coefficients[k] = (int) (Math.random()*11-1);
			}

			long startTime = System.currentTimeMillis();

			for(int i = 0; i < 1000; i++) {
				hornersMethod(coefficients, x);
			}

			long endTime = System.currentTimeMillis();

			double runTime = (endTime - startTime) / 1000.0;

			System.out.println(j + "          " + runTime);
		}
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
