package jalcon.math;

public class Position
{
	public Vector point;

	public Position(Vector point)
	{
		this.point = point;;
	}

	public Position(float x, float y)
	{
		this( new Vector(x, y) );
	}

	public Vector distance(Position other)
	{
		return other.point.clone().substract(this.point);
	}

	public void move(Vector movement)
	{
		this.point.add(movement);
	}

	public Position clone()
	{
		return new Position( this.point.clone() );
	}
}
