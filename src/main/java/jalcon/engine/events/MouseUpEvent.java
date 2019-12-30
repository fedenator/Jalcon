package jalcon.engine.events;

import jalcon.engine.math.*;

public class MouseUpEvent
{
	public final Position point;

	public MouseUpEvent(Position point)
	{
		this.point = point;
	}

	@Override
	public String toString()
	{
		return "MouseUpEvent {\n"
			+ "\tpoint: " + this.point + "\n"
			+ "}";
	}
}
