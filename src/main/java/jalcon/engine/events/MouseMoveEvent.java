package jalcon.engine.events;

import jalcon.engine.math.*;

public class MouseMoveEvent
{
	public final Position position;

	public MouseMoveEvent(Position position)
	{
		this.position = position;
	}

	@Override
	public String toString()
	{
		return "MouseMoveEvent {\n"
			+ "\tposition: " + this.position + "\n"
			+ "}";
	}
}
