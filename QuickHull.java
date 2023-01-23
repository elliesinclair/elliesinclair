import java.awt.Point;
import java.util.ArrayList;

public class QuickHull {

	public static void main(String[] args) {
		
		DUDraw.setCanvasSize(1000, 1000);
		DUDraw.setScale(-5, 105);
		
		int points = 50;
		Point set[] = new Point[points];
		
		for(int k = 0; k < points; k++) {
			int x = (int) (Math.random()*100);
			int y = (int) (Math.random()*100);
			set[k] = new Point(x, y);
			DUDraw.filledCircle(set[k].x, set[k].y, 1);
		}
		
		ArrayList<Point> qh = QuickHullStart(set);
		
		DUDraw.setPenColor(DUDraw.RED);
		
		for(int i = 0; i < qh.size(); i++) {
			DUDraw.filledCircle(qh.get(i).x, qh.get(i).y, 1);
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
	
	//Should take the set produced by upper(set)
//	public static void UpperHull(Point[] set, Point a, Point b, Point[] result) {
//		Point c = null;
//		//Line below feels incorrect
//		result[result.length] = c;
//		Point[] left = new Point[set.length];
//		Point[] right = new Point[set.length];
//		
//		int i = 0;
//		while(i < set.length) {
//			if(leftTurn(a, c, set[i])) {
//				left[i] = set[i];
//				i++;
//			}
//			else if(!leftTurn(b, c, set[i])) {
//				right[i] = set[i];
//				i++;
//			}
//		}
//		
//		UpperHull(left, a, c, result);
//		UpperHull(right, c, b, result);
//	}
	
	//Should take the set produced by lower(set)
//	public static void LowerHull(Point[] set, Point a, Point b, Point[] result) {
//		Point c = null;
//		result[result.length] = c; //add c to result
//		Point[] left = new Point[set.length];
//		Point[] right = new Point[set.length];
//		
//		int i = 0;
//		while(i < set.length) {
//			if(leftTurn(a, c, set[i])) {
//				left[i] = set[i];
//				i++;
//			}
//			else if(!leftTurn(b, c, set[i])) {
//				right[i] = set[i];
//				i++;
//			}
//		}
//		
//		LowerHull(left, a, c, result);
//		LowerHull(right, c, b, result);
//	}
	
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
	
	//Returns array of points above ab
	//public static Point[] upper(Point[] set) {
	//use left turn and not y coordinate
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
