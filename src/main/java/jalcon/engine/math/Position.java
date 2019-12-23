package jalcon.engine.math;

public class Position
{
	public float x;
	public float y;
	
	public Position(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public static Position sum(Position pos1, Position pos2)
	{
		return new Position(pos1.x + pos2.x, pos1.y + pos2.y);
	}
	public static Position negative(Position pos)
	{
		return new Position(-pos.x, -pos.y);
	}
}
