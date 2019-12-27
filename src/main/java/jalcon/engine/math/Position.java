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

	@Override
	public String toString()
	{
		return "Position[x: " + this.x + ", y: " + this.y + "]";
	}
}
