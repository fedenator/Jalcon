package jalcon.entities;

import java.util.Optional;

import jalcon.engine.Entity;
import jalcon.engine.Match;
import jalcon.engine.Renderer;
import jalcon.engine.graphics.NativeGraphicMedia;
import jalcon.math.Position;
import jalcon.math.Shape;
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
		int        owner_id
	)
	{
		super(planet_id, position, type, ships_count, owner_id);

		this.match = match;
		this.media = new NativeGraphicMedia(
			new Shape.Circle(this.type.radius)
		);

		Optional<Player> owner =
			this.match.get_player_by_id(this.owner_id);

		if ( !owner.isPresent() )
		{
			//TODO(fpalacios): Manejar incoherencias
			throw new RuntimeException("WTF: Player id no existe");
		}

		this.media.color = owner.get().player_color;
	}

	private void send_ships(PlanetEntity destination, int ammount)
	{
		this.ships_count -= ammount;
		//destination.recive_ships(this, ammount);
		this.match.add_ships(
			new ShipGroupEntity(
				this.match,
				0,
				destination.planet_id,
				this.owner_id,
				ammount,
				this.position.clone()
			)
		);
	}

	public void recive_ships(PlanetEntity source, int ammount)
	{
		if (source.owner_id == this.owner_id)
		{
			this.ships_count += ammount;
		}
		else
		{
			this.ships_count -= ammount;

			if (this.ships_count < 0)
			{
				Optional<Player> new_onwer =
					this.match.get_player_by_id(source.owner_id);

				if ( !new_onwer.isPresent() )
				{
					//TODO(fpalacios): Manejar incoherencias
					throw new RuntimeException("WTF: Player id no existe");
				}

				this.trasnfer_to_new_owner( new_onwer.get() );
			}
		}
	}

	private void trasnfer_to_new_owner(Player new_onwer)
	{
		this.ships_count = -this.ships_count;
		this.owner_id    = new_onwer.player_id;
		this.media.color = new_onwer.player_color;
		this.match.on_planet_converted();
	}

	@Override
	public void render(Renderer renderer)
	{
		this.media.render(this.position, 0, renderer);
		/*
		renderer.draw_string(
			Float.toString(this.ships_count),
			this.position.point.x,
			this.position.point.y,
			Color.BLACK
		);
		 */
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
				//TODO(fpalacios): Manejar incoherencias
				System.out.println("WTF: Se enviarion naves a un planeta que no existe");
			}

			PlanetEntity destination = destination_opt.get();
			this.send_ships(destination, send_ship_event.ammount);

		}
	}
}
