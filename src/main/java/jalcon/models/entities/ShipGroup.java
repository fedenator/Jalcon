package jalcon.models.entities;

import jalcon.engine.math.*;

public class ShipGroup
{
	public final int ship_id;
	public final int destination_planet_id;
	public final int owner_player_id;
	
	public Position position;
	
	public ShipGroup(
		int      ship_id,
		int      destination_planet_id,
		int      owner_player_id,
		Position position
	)
	{
		this.ship_id               = ship_id;
		this.destination_planet_id = destination_planet_id;
		this.owner_player_id       = owner_player_id;
		this.position              = position;
	}
}
