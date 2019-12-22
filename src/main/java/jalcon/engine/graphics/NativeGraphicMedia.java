package jalcon.engine.graphics;

import java.awt.Color;

import jalcon.engine.Renderer;
import jalcon.engine.math.Position;
import jalcon.engine.math.Shape;
import jalcon.engine.math.Shape.Circle;

public class NativeGraphicMedia implements IGraphicMedia
{
	private final Shape shape;
	
	public Color color;

	public NativeGraphicMedia(Shape shape) 
	{
		this.shape = shape;
		this.color = Color.CYAN;
	}

	@Override
	public void render(Position position, Renderer renderer) 
	{
		if (shape instanceof Circle)
		{
			float radius = ((Circle)this.shape).radius;
			renderer.draw_circle(position.x - radius / 2, position.y - radius / 2, radius, this.color);
		}
		else
		{
			throw new RuntimeException("WTF");
		}
	}
}
