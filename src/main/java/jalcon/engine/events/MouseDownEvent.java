package jalcon.engine.events;

import jalcon.engine.math.*;

public class MouseDownEvent
{
	public final Position point;

	public MouseDownEvent(Position point)
	{
		this.point = point;
	}

	@Override
	public String toString()
	{
		return "MouseDownEvent {\n"
			+ "\tpoint: " + this.point + "\n"
			+ "}";
	}
}
