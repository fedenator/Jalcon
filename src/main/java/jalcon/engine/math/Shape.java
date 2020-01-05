package jalcon.engine.math;

import java.awt.geom.Rectangle2D;

public abstract class Shape {
	// public abstract boolean intersects(Shape s);
	// public abstract boolean contains(Shape s);
	public abstract Dimension get_dimension();
	public abstract Rectangle2D get_bounds();

	public static class Circle extends Shape
	{
		public float radius;

		public Circle(float radius)
		{
			this.radius = radius;
		}
		
		public Dimension get_dimension()
		{
			return new Dimension(this.radius * 2, this.radius * 2);
		}
		public Rectangle2D get_bounds()
		{
			return new Rectangle2D.Float(-this.radius, -this.radius, this.radius * 2, this.radius * 2);
		}
	}

	public static class Polygon extends Shape
	{
		public int []points_x;
		public int []points_y;
		public int     points;
		
		public Polygon(Position...positions)
		{
			this.points = positions.length;
			this.points_x = new int[this.points];
			this.points_y = new int[this.points];
			for(int i = 0; i < positions.length; i++)
			{
				this.points_x[i] = (int)positions[i].point.x;
				this.points_y[i] = (int)positions[i].point.y;
			}
		}
		
		@Override
		public Dimension get_dimension() {
			Rectangle2D bounds = get_bounds();
			return new Dimension((int)bounds.getWidth(), (int)bounds.getHeight());
		}
		
		public Rectangle2D get_bounds()
		{
			int minX = Integer.MAX_VALUE;
			int maxX = Integer.MIN_VALUE;
			for (int x : points_x)
			{
				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
			}
			int minY = Integer.MAX_VALUE;
			int maxY = Integer.MIN_VALUE;
			for (int y : points_y)
			{
				minY = Math.min(minY, y);
				maxY = Math.max(maxY, y);
			}
			return new Rectangle2D.Float(minX, minY, maxX - minX, maxY - minY);
		}
	}
}
