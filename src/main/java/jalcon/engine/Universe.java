package jalcon.engine;

import java.util.ArrayList;

import jalcon.models.events.Event;

public class Universe {
	private final ArrayList<Entity> entities;
	public Universe() {
		this.entities = new ArrayList<>();
	}
	
	public synchronized void process_events(Event event)
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

	public synchronized void render(Renderer renderer)
	{
		for (Entity entity : this.entities)
		{
			entity.render(renderer);
		}
	}

	public synchronized void add_entity(Entity entity)
	{
		this.entities.add(entity);
	}

}
