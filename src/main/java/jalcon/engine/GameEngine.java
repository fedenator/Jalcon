package jalcon.engine;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentLinkedQueue;

import jalcon.models.events.Event;
import jalcon.plataform.PlataformRenderer;

public class GameEngine
implements
	Runnable
{
	private static final int WIDTH  = 600;
	private static final int HEIGHT = 400;

	private static final int FPS_DESIRED = 60;

	private final BufferedImage     buffer;
	private final Renderer          renderer;
	public  final Universe          universe;
	
	public boolean running = false;
	public boolean rendering = false;
	private Thread renderThread;

	private final ConcurrentLinkedQueue<Event> input_events;

	public GameEngine(PlataformRenderer platform_renderer)
	{
		this.universe     = new Universe();
		this.input_events = new ConcurrentLinkedQueue<>();

		this.buffer = new BufferedImage(
			WIDTH,
			HEIGHT,
			BufferedImage.TYPE_INT_ARGB
		);

		this.renderer = new Renderer(platform_renderer, this.buffer);
		this.renderer.background_color = Color.WHITE;

		new Thread(this).start();
	}

	private void process_events()
	{
		while ( !this.input_events.isEmpty() )
		{
			this.universe.process_events( this.input_events.poll() );
		}
	}

	private void update(long delta)
	{
		this.universe.update(delta);
	}

	private void render()
	{
		this.universe.render(this.renderer);
		this.renderer.do_render();
	}

	@Override
	public void run()
	{
		this.running = true;
		this.renderThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				rendering = true;
				while(rendering)
				{
					render();
				}
			}
		});
		this.renderThread.start();
		
		while (this.running)
		{
			this.process_events();
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
		this.rendering = false;
		try {
			this.renderThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void add_event(Event event)
	{
		this.input_events.add(event);
	}
}
