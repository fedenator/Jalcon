package jalcon.engine.events;

import jalcon.engine.math.*;

public class DraggedEvent
{
	public final Position source;
	public final Position destination;

	public DraggedEvent(Position source, Position destination)
	{
		this.source      = source;
		this.destination = destination;
	}

	@Override
	public String toString()
	{
		return "DraggingEvent {\n"
			+ "\tsource: " + this.source
			+ "\tdestination: " + this.destination
			+ "}";
	}
}
