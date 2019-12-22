package jalcon.engine;

import jalcon.models.events.Event;

public interface Entity
{
	public void render       (Renderer   renderer);
	public void update       (long       delta);
	public void process_event(Event      event);
}
