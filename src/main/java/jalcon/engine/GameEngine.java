package jalcon.engine;

import java.util.*;

import java.awt.*;
import java.awt.image.*;

import jalcon.entities.*;
import jalcon.plataform.*;
import jalcon.engine.math.*;
import jalcon.models.entities.*;

public class GameEngine
implements
	Runnable
{
	private static final int WIDTH  = 600;
	private static final int HEIGHT = 400;

	private static final int FPS_DESIRED = 60;

	private final BufferedImage     buffer;
	private final PlataformRenderer plataformRenderer;

	private final ArrayList<Entity> entities;

	public GameEngine(PlataformRenderer plataformRenderer)
	{
		this.entities = new ArrayList<>();

		this.buffer = new BufferedImage(
			WIDTH,
			HEIGHT,
			BufferedImage.TYPE_INT_ARGB
		);

		this.plataformRenderer = plataformRenderer;

		new Thread(this).start();
	}

	private void debug()
	{
		this.entities.add(
			new PlanetEntity(
				0,
				new Position(100, 100),
				PlanetType.TYPE_0,
				0,
				null
			)
		);
	}

	private void update(long delta)
	{
		for (Entity entity : this.entities)
		{
			entity.update(delta);
		}
	}

	private void render()
	{
		Graphics2D g2d = (Graphics2D) this.buffer.getGraphics();

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);

		for (Entity entity : this.entities)
		{
			entity.render(g2d);
		}

		this.plataformRenderer.render(this.buffer);
	}

	@Override
	public void run()
	{
		this.debug();
		while (true)
		{
			this.render();
			this.update( (long) (1000f / FPS_DESIRED) );
			try
			{
				Thread.sleep( (long) (1000f / FPS_DESIRED) );
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
