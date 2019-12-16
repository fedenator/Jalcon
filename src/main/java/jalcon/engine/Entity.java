package jalcon.engine;

import java.awt.*;

public interface Entity
{
	public void render(Graphics2D g2d);
	public void update(long delta);
}
