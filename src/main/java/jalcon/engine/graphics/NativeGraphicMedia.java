package jalcon.engine.graphics;

import java.awt.Color;

import jalcon.engine.Renderer;
import jalcon.engine.Renderer.RenderData;
import jalcon.math.Position;
import jalcon.math.Shape;

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
	public void render(Position position, double angle, Renderer renderer) 
	{
		RenderData rdata = new RenderData();
		rdata.position = position.clone();
		rdata.angle = angle;
		rdata.media = new Renderer.RenderShapeMedia(this.shape, Renderer.RenderShapeMedia.RenderShapeMode.Draw , this.color);
		renderer.to_render(rdata);
	}
}
