import java.awt.Point;

public class ConvexHullBruteForce {
	public static void main(String[] args) {
		int points = 50;
		Point set[] = new Point[points];
		
		for(int k = 0; k < points; k++) {
			int x = (int) (Math.random()*100);
			int y = (int) (Math.random()*100);
			set[k] = new Point(x, y);
		}
		
		BruteForce(set);
	}
	
	public static boolean leftTurn(Point a, Point b, Point i) {
		int VabX = b.x - a.x;
		int VabY = b.y - a.y;
		int VbiX = i.x - b.x;
		int VbiY = i.y - b.y;
		int crossProduct = (VabX*VbiY)-(VabY*VbiX);
		return crossProduct > 0;
	}
	
	public static void BruteForce(Point[] set) {
		int n = set.length;
		int l = 0;

		Line[] CH = new Line[(n-1)*n/2];
		for(int i = 0; i < set.length; i ++) {
			for(int j = 0; j < set.length; j++) {
				if(set[i] != set[j]) {
					int LeftTurnCount = 0;
					for(int k = 0; k < set.length; k++) {
						if(leftTurn(set[i], set[j], set[k])) {
							LeftTurnCount++;
						}
					}
					if(LeftTurnCount == 0 || LeftTurnCount == set.length-2) {
						CH[l] = new Line(set[i], set[j]);
						//System.out.println(printLine(CH[l]));
						l++;
					}
				}
			}
		}
	}
	
	public static void printArray(Line[] A) {
		String temp = "";
		for(int i = 0; i < A.length; i++) {
			temp += printLine(A[i]) + " ";
		}
		System.out.println(temp);
	}
	
	public static String printLine(Line line) {
		return "Start point: (" + line.getStartX() + ", " + line.getStartY() + "), End Point: (" + line.getEndX() + ", " + line.getEndY() + ")";
	}
}
