package jalcon.entities;

import java.awt.Color;
import java.util.Optional;

import jalcon.engine.Entity;
import jalcon.engine.Match;
import jalcon.engine.Renderer;
import jalcon.engine.graphics.NativeGraphicMedia;
import jalcon.engine.math.Position;
import jalcon.engine.math.Shape;
import jalcon.models.entities.Planet;
import jalcon.models.entities.PlanetType;
import jalcon.models.entities.Player;
import jalcon.models.events.Event;
import jalcon.models.events.SendShipsEvent;

public class PlanetEntity
extends Planet
implements
	Entity
{
	private final Match match;
	private NativeGraphicMedia media;

	public PlanetEntity(
		Match      match,
		int        planet_id,
		Position   position,
		PlanetType type,
		float      ships_count,
		Player     owner
	)
	{
		super(
			planet_id,
			position,
			type,
			ships_count,
			owner
		);

		this.match = match;
		this.media = new NativeGraphicMedia(new Shape.Circle(this.type.radius));
		this.media.color = Color.RED;
	}

	private void send_ships(PlanetEntity destination, int ammount)
	{
		this.ships_count -= ammount;
		destination.ships_count -= ammount;
	}

	@Override
	public void render(Renderer renderer)
	{
		this.media.render(this.position, 0, renderer);
		renderer.draw_string(
			Float.toString(this.ships_count),
			this.position.x,
			this.position.y,
			Color.BLACK
		);
	}

	@Override
	public void update(long delta)
	{
		this.ships_count += ( (float) delta / 1000f ) * this.type.production_per_second;
	}

	@Override
	public void process_event(Event event) {
		if (event instanceof SendShipsEvent)
		{
			SendShipsEvent send_ship_event = (SendShipsEvent) event;

			if (send_ship_event.source_planet_id != this.planet_id)
			{
				return;
			}

			Optional<PlanetEntity> destination_opt = this.match.get_planet_by_id(
				send_ship_event.destination_player_id
			);

			if ( !destination_opt.isPresent() )
			{
				System.out.println("WARN: Se enviarion naves a un planeta que no existe");
			}

			PlanetEntity destination = destination_opt.get();
			this.send_ships(destination, send_ship_event.ammount);

		}
	}
}
