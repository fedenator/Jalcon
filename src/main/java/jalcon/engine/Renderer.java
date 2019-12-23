package jalcon.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import jalcon.plataform.PlataformRenderer;

public class Renderer 
{
	private final PlataformRenderer platform_renderer;
	private final BufferedImage     canvas;
	
	public Color background_color;
	
	public Renderer(
		PlataformRenderer platform_renderer,
		BufferedImage     canvas
	) 
	{
		this.platform_renderer = platform_renderer;
		this.canvas            = canvas;
		this.background_color  = Color.MAGENTA;
	}
	
	public void draw_circle(
		float x,
		float y,
		float r,
		Color color
	)
	{
		Graphics2D g2d = (Graphics2D) this.canvas.getGraphics();
		g2d.setColor(color);
		g2d.fillOval((int)x, (int)y, (int)r, (int)r);
	}
	
	public void do_render()
	{
		// TODO (sendar): draw on another thread
		this.platform_renderer.render(this.canvas);
	}

	public void draw_string(
		String string, 
		float  x, 
		float  y, 
		Color  color
	)
	{
		Graphics2D g2d = (Graphics2D) this.canvas.getGraphics();
		g2d.setColor(color);
		g2d.drawString(string, x, y);
	}

	public void clear() {
		Graphics2D g2d = (Graphics2D) this.canvas.getGraphics();

		g2d.setColor(this.background_color);
		g2d.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
	}

	public void draw_triangle(
		double x1, 
		double y1, 
		double x2, 
		double y2, 
		double x3, 
		double y3, 
		Color  color
	) 
	{
		Graphics2D g2d = (Graphics2D) this.canvas.getGraphics();
		g2d.setColor(color);
		g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		g2d.drawLine((int)x2, (int)y2, (int)x3, (int)y3);
		g2d.drawLine((int)x3, (int)y3, (int)x1, (int)y1);
	}
}
