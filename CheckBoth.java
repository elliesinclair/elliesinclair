import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CheckBoth {
	public static void main(String[] args) {

		DUDraw.setCanvasSize(500, 500);
		DUDraw.setScale(-5, 105);
		
		int points = 50;
		ArrayList<Point> set = new ArrayList<Point>();
		Point[] setArray = new Point[points];
		
		for(int k = 0; k < points; k++) {
			int x = (int) (Math.random()*100);
			int y = (int) (Math.random()*100);
			Point point = new Point(x, y);
			set.add(point);
			setArray[k] = point;
			DUDraw.filledCircle(set.get(k).x, set.get(k).y, 0.7);
		}
		Point[] closestpairDAC = closestPair(set);
		Point[] closestpairBruteForce = BruteForce(setArray);
		System.out.println("(" + closestpairDAC[0].x + ", " + closestpairDAC[0].y + ") (" + closestpairDAC[1].x + ", " + closestpairDAC[1].y + ")");
		System.out.println("(" + closestpairBruteForce[0].x + ", " + closestpairBruteForce[0].y + ") (" + closestpairBruteForce[1].x + ", " + closestpairBruteForce[1].y + ")");

		
		DUDraw.setPenColor(DUDraw.RED);
		DUDraw.filledCircle(closestpairDAC[0].x, closestpairDAC[0].y, 0.7);
		DUDraw.filledCircle(closestpairDAC[1].x, closestpairDAC[1].y, 0.7);
	}
	
	public static Point[] closestPairRecursive(ArrayList<Point> Ax, int p, int r, ArrayList<Point> Ay) {
		//Base case:
		int n = r-p+1;
		if(n <= 3) {
			if(n < 2)
			{
				System.out.println("error: base case is less than 2");
			}
			Point[] points = new Point[n];
			for(int i = p; i <= r; i++) {
				points[i-p] = Ax.get(i);
			}
			//System.out.println("A:" + points);
			//System.out.println(points.length);
			return BruteForce(points);
		}
		
		//Other cases:
		int m = (p+r)/2;
		Point[] returnPoints = new Point[2];
		
		ArrayList<Point> yLeft = new ArrayList<Point>();
		ArrayList<Point> yRight = new ArrayList<Point>();
		
		for(int i = 0; i < Ay.size(); i++) {
			if(Ay.get(i).x <= Ax.get(m).x) {
				yLeft.add(Ay.get(i));
			}
			else {
				yRight.add(Ay.get(i));
			}
		}
		
		Point[] leftPoints = closestPairRecursive(Ax, p, m, yLeft);
		Point[] rightPoints = closestPairRecursive(Ax, m+1, r, yRight);
		
		double leftDistance = distance(leftPoints[0], leftPoints[1]);
		double rightDistance = distance(rightPoints[0], rightPoints[1]);
		double minDistance = rightDistance;
		returnPoints[0] = rightPoints[0];
		returnPoints[1] = rightPoints[1];
		
		if(leftDistance <= minDistance) {
			minDistance = leftDistance;
			returnPoints[0] = leftPoints[0];
			returnPoints[1] = leftPoints[1];
		}
		
		ArrayList<Point> band = new ArrayList<Point>();
		
		for(int i = 0; i < Ay.size(); i++) { 
			if(Math.abs(Ay.get(i).x - Ax.get(m).x) <= minDistance) {
				band.add(Ay.get(i));
			}
		}
		
		
		//check all 8 points
		for(int i = 0; i < band.size(); i++) {
			for(int j = i+1; j <= i+7 && j < band.size(); j++) {
				double distance = distance(band.get(i), band.get(j));
				
				if(distance < minDistance) {
					minDistance = distance;
					returnPoints[0] = band.get(i);
					returnPoints[1] = band.get(j);
				}
			}
		}
		
		return returnPoints;
	}
	
	public static Point[] closestPair(ArrayList<Point> A) {
		
		Point[] closestPair = new Point[2];
		
		ArrayList<Point> X = new ArrayList<Point>(A);
		Collections.sort(X, new Comparator<Point>() {
            @Override public int compare(Point p1, Point p2)
            {
                return p1.x - p2.x; 
            }
        });
		
		ArrayList<Point> Y = new ArrayList<Point>(A);
		Collections.sort(Y, new Comparator<Point>() {
            @Override 
            public int compare(Point p1, Point p2)
            {
                return p1.y - p2.y; 
            }
        });

		return closestPairRecursive(X, 0, X.size()-1, Y);
	}

	public static double distance(Point a, Point b) {
		double xDiff = (double) (b.x - a.x);
		double yDiff = (double) (b.y - a.y);
		double distance = Math.pow(xDiff, 2.0) + Math.pow(yDiff, 2.0);
		distance = Math.sqrt(distance);
		return distance;
	}
	
	public static void printPoints(Point[] A) {
		for(int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}
	
	public static Point[] BruteForce(Point[] set) {
		Point[] closestPair = new Point[2];
		
		closestPair[0] = set[0];
		closestPair[1] = set[1];
		
		double minDistance = distance(set[0], set[1]); 
		
		for(int i = 0; i < set.length; i++) {
			for(int j = i+1; j <set.length; j++) {
				
				double distance = distance(set[i], set[j]);
				
				if(distance < minDistance) {
					minDistance = distance;
					closestPair[0] = set[i];
					closestPair[1] = set[j];
				}
			}
		}
		return closestPair;
	}
}
