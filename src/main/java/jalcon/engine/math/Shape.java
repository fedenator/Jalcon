package jalcon.engine.math;

public abstract class Shape {
	// public abstract boolean intersects(Shape s);
	// public abstract boolean contains(Shape s);

	public static class Circle extends Shape
	{
		public float radius;

		public Circle(float radius)
		{
			this.radius = radius;
		}
	}
	
	public static class Triangle extends Shape
	{
		public Position points[];
		public Triangle(
			Position point1,
			Position point2,
			Position point3
		) 
		{
			points = new Position[] { point1, point2, point3 };
		}
	}

	/*
	 * TODO
	public static class Polygon extends Shape
	{
		public ArrayList<Position> points;
		
		public Polygon(Position...positions)
		{
			this.points = new ArrayList<>(positions.length);
			for(int i = 0; i < positions.length; i++)
				this.points.add(positions[i]);
		}
	}
	*/
}
