import java.awt.Point;
import java.util.ArrayList;

public class QuickHullRuntime {

	public static void main(String[] args) {
		System.out.println("Brute Force Convex Hull:");
		System.out.println("N          Runtime");
		
		for(int j = 10; j < 10000; j+= 10) {
			
			int points = j;
			Point set[] = new Point[points];
			
			for(int k = 0; k < points; k++) {
				int x = (int) (Math.random()*100);
				int y = (int) (Math.random()*100);
				set[k] = new Point(x, y);
			}

			long startTime = System.currentTimeMillis();

			for(int i = 0; i < 1000; i++) {
				ArrayList<Point> qh = QuickHullStart(set);
			}

			long endTime = System.currentTimeMillis();

			double runTime = (endTime - startTime) / 1000.0;

			System.out.println(j + "          " + runTime);
		}

	}
	public static Point minX(Point[] set) {
		Point min = set[0];
		int minX = set[0].x;
		for(int i = 0; i < set.length; i++) {
			if(set[i].x < minX) {
				minX = set[i].x;
				min = set[i];
			}
		}
		return min;
	}
	
	public static Point maxX(Point[] set) {
		Point max = set[0];
		int maxX = set[0].x;
		for(int i = 0; i < set.length; i++) {
			if(set[i].x > maxX) {
				maxX = set[i].x;
				max = set[i];
			}
		}
		return max;
	}
	
	public static ArrayList<Point> QuickHullStart(Point[] set) {
		Point a = minX(set);
		Point b = maxX(set);
		ArrayList<Point> Supper = upper(set); //all points above line ab
		ArrayList<Point> Slower = lower(set); //all points below line ab (above ba)
		ArrayList<Point> convexHull = new ArrayList<Point>();
		convexHull.add(a);
		convexHull.add(b);
		QuickHull(a, b, Supper, convexHull);
		QuickHull(b, a, Slower, convexHull);
		
		return convexHull;
	}
	
	public static void QuickHull(Point a, Point b, ArrayList<Point> set, ArrayList<Point> convexHull) {
		if(set.isEmpty()) {
			return;
		}
		Point c = furthestPoint(set, a, b); //index of point with max distance from ab on the upper half
		ArrayList<Point> A = new ArrayList<Point>(); //set of points strictly left of (a, c)
		ArrayList<Point> B = new ArrayList<Point>(); //set of points strictly left of (c, b)

		convexHull.add(c);
		set.remove(c);
		
		for(int i = 0; i < set.size(); i++) {
			if(leftTurn(a, c, set.get(i))) {
				A.add(set.get(i));
			}
			else if(!leftTurn(b, c, set.get(i))) {
				B.add(set.get(i));
			}
		}
		
		QuickHull(a, c, A, convexHull);
		QuickHull(c, b, B, convexHull);
	}
	
	public static int valueBasedOnLineDistance(Point a, Point b, Point p) {
		int v1x = b.x - a.x;
		int v1y = b.y - a.y;
		int v2x = p.x - a.x;
		int v2y = p.y - a.y;
		return Math.abs(v1x * v2y - v1y * v2x);
	}
	
	public static boolean leftTurn(Point a, Point b, Point i) {
		int VabX = b.x - a.x;
		int VabY = b.y - a.y;
		int VbiX = i.x - b.x;
		int VbiY = i.y - b.y;
		int crossProduct = (VabX*VbiY)-(VabY*VbiX);
		return crossProduct > 0;
	}

	public static ArrayList<Point> upper(Point[] set) {
		Point a = minX(set);
		Point b = maxX(set);
		
		ArrayList<Point> tempSet = new ArrayList<Point>();
		
		for(int i = 0; i < set.length; i++) {
			if(leftTurn(a, b, set[i])) {
				tempSet.add(set[i]);
			}
		}
		return tempSet;
	}
	
	public static ArrayList<Point> lower(Point[] set) {
		Point a = minX(set);
		Point b = maxX(set);
		
		ArrayList<Point> tempSet = new ArrayList<Point>();		
		for(int i = 0; i < set.length; i++) {
			if(leftTurn(b, a, set[i])) {
				tempSet.add(set[i]);
			}
		}
		return tempSet;
	}
	
	public static Point furthestPoint(ArrayList<Point> set, Point a, Point b) {
		
		Point c = set.get(0);
		int temp = valueBasedOnLineDistance(a, b, c);
		
		for(int i = 0; i < set.size(); i++) {
			if(valueBasedOnLineDistance(a, b, set.get(i)) > temp) {
				c = set.get(i);
				temp = valueBasedOnLineDistance(a, b, c);
			}
		}
		return c;
	}

}
