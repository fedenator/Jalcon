package jalcon.math;

public class Dimension
{
	public Vector size;

	public Dimension(Vector size)
	{
		this.size = size;
	}

	public Dimension(float x, float y)
	{
		this( new Vector(x, y) );
	}

	public Dimension clone()
	{
		return new Dimension( this.size.clone() );
	}
	
	public Position get_center()
	{
		return new Position(size.clone().divide(2));
	}
}
