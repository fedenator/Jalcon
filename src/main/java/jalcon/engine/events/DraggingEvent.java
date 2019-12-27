package jalcon.engine.events;

import jalcon.engine.math.*;

/**
 * Un evento que reprecenta un drag en progreso
 */
public class DraggingEvent
{
	public final Position source;
	public final Position destination;

	public DraggingEvent(Position source, Position destination)
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
