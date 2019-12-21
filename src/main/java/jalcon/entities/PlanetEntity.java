package jalcon.entities;

import java.awt.*;
import java.util.*;

import jalcon.engine.*;
import jalcon.engine.math.*;
import jalcon.models.entities.*;
import jalcon.models.events.*;
import jalcon.models.events.Event;

public class PlanetEntity
extends Planet
implements
	Entity
{
	private final GameEngine game_engine;

	public PlanetEntity(
		GameEngine game_engine,
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

		this.game_engine = game_engine;
	}

	private void send_ships(PlanetEntity destination, int ammount)
	{
		this.ships_count -= ammount;
		destination.ships_count -= ammount;
	}

	@Override
	public void render(Graphics2D g2d)
	{
		g2d.setColor(Color.RED);
		g2d.fillOval(
			(int) ( this.position.x - (this.type.radius / 2) ),
			(int) ( this.position.y - (this.type.radius / 2) ),
			(int) ( this.type.radius                         ),
			(int) ( this.type.radius                         )
		);

		g2d.setColor(Color.BLACK);
		g2d.drawString(
			Float.toString(this.ships_count),
			this.position.x,
			this.position.y
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

			Optional<PlanetEntity> destination_opt = this.game_engine.get_planet_by_id(
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
