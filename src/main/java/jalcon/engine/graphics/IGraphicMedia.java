package jalcon.engine.graphics;

import jalcon.engine.Renderer;
import jalcon.engine.math.Position;

public interface IGraphicMedia 
{
	public void render(Position position, double angle, Renderer renderer);
}
