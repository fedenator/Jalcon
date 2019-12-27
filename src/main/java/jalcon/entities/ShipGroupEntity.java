package jalcon.entities;

import java.util.*;

import jalcon.math.*;
import jalcon.engine.*;
import jalcon.models.events.*;
import jalcon.engine.graphics.*;
import jalcon.models.entities.*;

public class ShipGroupEntity
extends ShipGroup
implements
	Entity
{
	private static final float VELOCITY_PER_SECOND = 50f;

	private final Match              match;
	private final NativeGraphicMedia media;

	public ShipGroupEntity(
		Match    match,
		int      ship_id,
		int      destination_planet_id,
		int      owner_player_id,
		int      ammount,
		Position position
	)
	{
		super(
			ship_id,
			destination_planet_id,
			owner_player_id,
			ammount,
			position
		);

		this.match = match;
		this.media = new NativeGraphicMedia( new Shape.Circle(20) );
	}

	@Override
	public void update(long delta)
	{
		Optional<PlanetEntity> dest_planet_opt =
			this.match.get_planet_by_id(this.destination_planet_id);

		if ( dest_planet_opt.isEmpty() )
		{
			//TODO(fpalacios): Manejar incoherencias
			throw new RuntimeException("WTF: Planet id no existe");
		}

		PlanetEntity dest_planet = dest_planet_opt.get();

		// TODO(fpalacios): Calcular la direccion una vez
		// los planetas no se mueven
		jalcon.math.Vector direcction =
			this.position.distance(dest_planet.position).normalize();

		direcction.normalize().muliply( (VELOCITY_PER_SECOND / 1000) * delta );

		this.position.move(direcction);
	}

	@Override
	public void render(Renderer renderer)
	{
		this.media.render(this.position, renderer);
	}

	@Override
	public void process_event(Event event)
	{

	}
}
