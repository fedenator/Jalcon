package jalcon.engine;

import java.awt.*;
import java.awt.image.*;

import jalcon.plataform.*;

public class GameEngine
implements
	Runnable
{
	private static final int WIDTH  = 600;
	private static final int HEIGHT = 400;

	private static final int FPS_DESIRED = 60;

	private final BufferedImage     buffer;
	private final PlataformRenderer plataformRenderer;

	public GameEngine(PlataformRenderer plataformRenderer)
	{
		this.buffer = new BufferedImage(
			WIDTH,
			HEIGHT,
			BufferedImage.TYPE_INT_ARGB
		);

		this.plataformRenderer = plataformRenderer;

		new Thread(this).start();
	}

	private void render()
	{
		Graphics2D g2d = (Graphics2D) this.buffer.getGraphics();

		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);

		this.plataformRenderer.render(this.buffer);
	}

	@Override
	public void run()
	{
		while (true)
		{
			this.render();
			try
			{
				Thread.sleep(60 / FPS_DESIRED * 1000);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
