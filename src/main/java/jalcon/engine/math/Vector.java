package jalcon.engine.math;

public class Vector
{
	public float x;
	public float y;

	public Vector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector clone()
	{
		return new Vector(this.x, this.y);
	}

	public Vector add(Vector other)
	{
		this.x += other.x;
		this.y += other.y;
		return this;
	}

	public Vector substract(Vector other)
	{
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public Vector muliply(float factor)
	{
		this.x *= factor;
		this.y *= factor;
		return this;
	}

	public Vector divide(float divisor)
	{
		this.x /= divisor;
		this.y /= divisor;
		return this;
	}

	public float module()
	{
		return (float) Math.sqrt( Math.pow(this.x, 2) + Math.pow(this.y, 2) );
	}

	public Vector normalize()
	{
		this.divide( this.module() );
		return this;
	}
}
