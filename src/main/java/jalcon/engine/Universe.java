package jalcon.engine;

import java.util.concurrent.*;

import jalcon.models.events.Event;

public class Universe
{
	// TODO(fpalacios): Remplazar por buffer para agregar
	private final ConcurrentLinkedQueue<Entity> entities;

	public Universe()
	{
		this.entities = new ConcurrentLinkedQueue<>();
	}

	public void process_events(Event event)
	{
		for (Entity entity : this.entities)
		{
			entity.process_event(event);
		}
	}

	public void update(long delta)
	{
		for (Entity entity : this.entities)
		{
			entity.update(delta);
		}
	}

	public void render(Renderer renderer)
	{
		for (Entity entity : this.entities)
		{
			entity.render(renderer);
		}
	}

	public void add_entity(Entity entity)
	{
		this.entities.add(entity);
	}

}
