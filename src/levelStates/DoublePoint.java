package levelStates;

import java.awt.Point;
import java.awt.geom.Point2D;


/** Double point class, useful to store double values
 *  @author Tibor Fiak */
public class DoublePoint extends Point2D {
		//x and y...
		public double x;
		public double y;
		
		//class constructor
		public DoublePoint() { x = 0; y = 0; }
		public DoublePoint(double x, double y) { setLocation(x, y); }
		
		//get x and y
		public double getX() { return x; }
		public double getY() { return y; }

		//setLocation
		public void setLocation(double x, double y) { this.x = x; this.y = y; }

		//toString and toPoint
		public String toString() { return "DoublePoint[x: "+x+", y: "+y+"]"; }
		public Point toPoint() { return new Point((int)Math.round(this.x), (int)Math.round(this.y)); } }