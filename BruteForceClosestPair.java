import java.awt.Point;
import java.util.ArrayList;

public class BruteForceClosestPair {

	public static void main(String[] args) {
		
		DUDraw.setCanvasSize(500, 500);
		DUDraw.setScale(-5, 105);
		
		int points = 50;
		Point set[] = new Point[points];
		
		for(int k = 0; k < points; k++) {
			int x = (int) (Math.random()*100);
			int y = (int) (Math.random()*100);
			set[k] = new Point(x, y);
			DUDraw.filledCircle(set[k].x, set[k].y, 0.7);
		}
		
		ClosestPairBruteForce(set);
		
	}
	
	public static Point[] ClosestPairBruteForce(Point[] xSet) {
		Point[] closestPair = new Point[2];
		
		closestPair[0] = xSet[0];
		closestPair[1] = xSet[1];
		
		double minDistance = distance(xSet[0], xSet[1]); 
		
		for(int i = 0; i < xSet.length; i++) {
			for(int j = i+1; j <xSet.length; j++) {
				
				double distance = distance(xSet[i], xSet[j]);
				
				if(distance < minDistance) {
					minDistance = distance;
					closestPair[0] = xSet[i];
					closestPair[1] = xSet[j];
				}
			}
		}
		
		System.out.println("(" + closestPair[0].x + ", " + closestPair[0].y + ") (" + closestPair[1].x + ", " + closestPair[1].y + ")");
		DUDraw.setPenColor(DUDraw.RED);
		DUDraw.filledCircle(closestPair[0].x, closestPair[0].y, 0.7);
		DUDraw.filledCircle(closestPair[1].x, closestPair[1].y, 0.7);
		
		return closestPair;
	}
	
	public static double distance(Point a, Point b) {
		double xDiff = (double) (b.x - a.x);
		double yDiff = (double) (b.y - a.y);
		double distance = Math.pow(xDiff, 2.0) + Math.pow(yDiff, 2.0);
		distance = Math.sqrt(distance);
		return distance;
	}
}
