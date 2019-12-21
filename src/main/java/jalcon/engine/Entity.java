package jalcon.engine;

import java.awt.*;

import jalcon.models.events.Event;

public interface Entity
{
	public void render       (Graphics2D g2d  );
	public void update       (long       delta);
	public void process_event(Event      event);
}
