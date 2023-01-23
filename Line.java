import java.awt.Point;

public class Line {
	public Line line;
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	
	public Line(Point a, Point b) {
		startX = a.x;
		startY = a.y;
		endX = b.x;
		endY = b.y;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}
	
	public String printLine(Line line) {
		return "Start point: (" + line.getStartX() + ", " + line.getStartY() + "), End Point: (" + line.getEndX() + ", " + line.getEndY() + ")";
	}
 }
