package jalcon.engine.math;

import java.awt.Point;
import java.awt.Polygon;

public class Shape {
	enum ShapeType
	{
		Circle,
		Polygon
	}
	
	ShapeType type = null;
	Polygon polygon;
	Circle circle;
	
	public ShapeType getType()
	{
		return type;
	}

	public boolean intersects(Shape s)
	{
		// TODO
		return false;
	}
	
	public class Circle
	{
		public Point position;
		public float radius;
		
		public boolean contains(Point p)
		{
			return position.distance(p) <= radius;
		}
	}
	public class Polygon
	{
		
	}
	
	
}
