package jalcon.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.LinkedBlockingQueue;

import jalcon.engine.Renderer.RenderShapeMedia.RenderShapeMode;
import jalcon.engine.math.Position;
import jalcon.engine.math.Shape;
import jalcon.engine.math.Shape.Polygon;
import jalcon.plataform.PlataformRenderer;

public class Renderer
{
	private final PlataformRenderer platform_renderer;
	private final BufferedImage     canvas;
	
	public Color background_color;
	public LinkedBlockingQueue<RenderData> to_render;
	public Object mutex = new Object();

	public Renderer(
		PlataformRenderer platform_renderer,
		BufferedImage     canvas
	) 
	{
		this.platform_renderer = platform_renderer;
		this.canvas            = canvas;
		this.background_color  = Color.MAGENTA;
		this.to_render         = new LinkedBlockingQueue<>();
	}

	public void to_render(RenderData data)
	{
		synchronized (mutex)
		{
			to_render.add(data);
		}
	}

	public void do_render()
	{
		LinkedBlockingQueue<RenderData> to_render;
		synchronized (mutex)
		{
			to_render = this.to_render;
			int capacity = Math.max(50, to_render.size());
			this.to_render = new LinkedBlockingQueue<RenderData>(capacity);
		}
		clear();
		RenderData data;
		while((data = to_render.poll()) != null)
		{
			do_render(data);
		}
		this.platform_renderer.render(this.canvas);
	}
	
	private void do_render(RenderData data)
	{
		assert data       != null;
		assert data.media != null;
		
		Graphics2D g2d = (Graphics2D) this.canvas.getGraphics();
		
		AffineTransform originalTransform = g2d.getTransform();
		AffineTransform transform = (AffineTransform)(originalTransform.clone());
		
		// Translation transform
		Rectangle2D bounds = data.media.get_bounds(g2d);
		transform.translate(data.position.point.x - bounds.getCenterX(), data.position.point.y - bounds.getCenterY());
		
		if ((data.angle % 2) !=  0) // Rotation transform
		{
			transform.rotate(data.angle, (int) bounds.getCenterX(), (int) bounds.getCenterY());
		}
		g2d.setTransform(transform);
		
		// Draw
		if (data.media instanceof RenderImageMedia)
		{
			draw_image(g2d, data);
		}
		else if (data.media instanceof RenderShapeMedia)
		{
			RenderShapeMedia media = (RenderShapeMedia) data.media;
			assert media.shape != null;
			
			boolean fill = media.mode == RenderShapeMode.Fill;
			if (media.shape instanceof Shape.Circle)
			{
				draw_circle(g2d, (Shape.Circle) media.shape, media.color, fill);
			}
			else if (media.shape instanceof Shape.Polygon)
			{
				draw_polygon(g2d, (Shape.Polygon) media.shape, media.color, fill);
			}
		}
		else if (data.media instanceof RenderTextMedia)
		{
			RenderTextMedia media = (RenderTextMedia) data.media;
			assert media.font  != null;
			assert media.color != null;
			
			draw_string(g2d, media.text, media.color);
		}
		
		g2d.setTransform(originalTransform);
	}
	
	public void draw_image(Graphics2D g2d, RenderData data)
	{
		assert data != null;
		assert data.media != null;
		assert data.media instanceof RenderImageMedia;
		RenderImageMedia media = (RenderImageMedia) data.media;

		g2d.drawImage(media.image, 0, 0, null);
	}
	
	public void draw_circle(
		Graphics2D g2d,
		Shape.Circle circle,
		Color        color,
		boolean      fill
	)
	{
		int r = (int)circle.radius;
		
		g2d.setColor(color);
		if (fill)
			g2d.fillOval(-r, -r, r*2, r*2);
		else
			g2d.drawOval(-r, -r, r*2, r*2);
	}

	public void draw_string(
		Graphics2D g2d,
		String string,
		Color  color
	)
	{
		g2d.setColor(color);
		g2d.drawString(string, 0, 0);
	}

	public void draw_polygon(
		Graphics2D g2d,
		Polygon polygon,
		Color   color,
		boolean fill
	)
	{
		g2d.setColor(color);
		if (fill)
			g2d.fillPolygon(polygon.points_x, polygon.points_y, polygon.points);
		else
			g2d.drawPolygon(polygon.points_x, polygon.points_y, polygon.points);
	}
	
	private void clear()
	{
		Graphics2D g2d = (Graphics2D) this.canvas.getGraphics();
		
		g2d.setColor(this.background_color);
		g2d.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
	}

	public static abstract class RenderMedia
	{
		public abstract Rectangle2D get_bounds(Graphics2D g2d);
	}
	public static class RenderShapeMedia extends RenderMedia
	{
		public enum RenderShapeMode
		{
			Fill,
			Draw
		}
		public Shape           shape;
		public Color           color;
		public RenderShapeMode mode;
		
		
		public RenderShapeMedia(
			Shape           shape,
			RenderShapeMode mode,
			Color           color
		)
		{
			this.shape = shape;
			this.mode  = mode;
			this.color = color;
		}
		
		public Rectangle2D get_bounds(Graphics2D g2d)
		{
			if (this.shape == null)
				return new Rectangle2D.Double(0, 0, 0, 0);
			return this.shape.get_bounds();
		}
	}
	public static class RenderImageMedia extends RenderMedia
	{
		public BufferedImage image;
		
		public RenderImageMedia(BufferedImage image)
		{
			this.image = image;
		}
		
		public Rectangle2D get_bounds(Graphics2D g2d)
		{
			if (this.image == null)
				return new Rectangle2D.Double(0, 0, 0, 0);
			return new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
		}
	}
	public static class RenderTextMedia extends RenderMedia
	{
		public String text;
		public Font   font;
		public Color  color;
		
		public RenderTextMedia(
			String text,
			Font   font,
			Color  color
		)
		{
			this.text  = text;
			this.font  = font;
			this.color = color;
		}
		
		public Rectangle2D get_bounds(Graphics2D g2d)
		{
			FontMetrics metrics = g2d.getFontMetrics(this.font);
			Rectangle2D bounds = metrics.getStringBounds(this.text, g2d);
			return bounds;
		}
	}
	public static class RenderData
	{
		public double angle; // In radians
		public Position position;
		public RenderMedia media;
		public RenderData() {
			this.position = new Position(0, 0);
		}
	}
}
