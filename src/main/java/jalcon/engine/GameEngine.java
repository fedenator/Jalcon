package jalcon.engine;

import java.util.*;
import java.util.concurrent.*;

import java.awt.*;
import java.awt.image.*;

import jalcon.entities.*;
import jalcon.plataform.*;
import jalcon.engine.math.*;
import jalcon.models.events.Event;
import jalcon.models.entities.*;

public class GameEngine
implements
	Runnable
{
	private static final int WIDTH  = 600;
	private static final int HEIGHT = 400;

	private static final int FPS_DESIRED = 60;

	private final BufferedImage     buffer;
	private final PlataformRenderer plataform_renderer;

	private final ArrayList<Entity>            entities;
	private final ConcurrentLinkedQueue<Event> input_events;

	public GameEngine(PlataformRenderer plataform_renderer)
	{
		this.entities     = new ArrayList<>();
		this.input_events = new ConcurrentLinkedQueue<>();

		this.buffer = new BufferedImage(
			WIDTH,
			HEIGHT,
			BufferedImage.TYPE_INT_ARGB
		);

		this.plataform_renderer = plataform_renderer;

		new Thread(this).start();
	}

	//DEBUG(fpalacios): Una forma rapida de spawnear entidades
	private void debug()
	{
		this.entities.add(
			new PlanetEntity(
				this,
				0,
				new Position(100, 100),
				PlanetType.TYPE_0,
				0,
				null
			)
		);
		this.entities.add(
			new PlanetEntity(
				this,
				1,
				new Position(200, 200),
				PlanetType.TYPE_0,
				0,
				null
			)
		);
	}

	private void process_events()
	{
		while ( !this.input_events.isEmpty() )
		{
			Event event = this.input_events.poll();
			for (Entity entity : this.entities)
			{
				entity.process_event(event);
			}
		}
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

		this.plataform_renderer.render(this.buffer);
	}

	@Override
	public void run()
	{
		this.debug();
		while (true)
		{
			this.process_events();
			this.update( (long) (1000f / FPS_DESIRED) );
			this.render();
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

	//FIXME(fpalacios): Hacer esto de una manera que no me haga quierer arrancarme los ojos
	public Optional<PlanetEntity> get_planet_by_id(int planet_id)
	{
		for (Entity entity : this.entities)
		{
			if (entity instanceof PlanetEntity)
			{
				PlanetEntity planet_entity = (PlanetEntity) entity;

				if (planet_entity.planet_id == planet_id)
				{
					return Optional.of(planet_entity);
				}
			}
		}

		return Optional.empty();
	}

	public void add_event(Event event)
	{
		this.input_events.add(event);
	}
}
