package jalcon.math;

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
}
