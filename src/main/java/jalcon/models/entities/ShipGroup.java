package jalcon.models.entities;

import jalcon.math.*;

public class ShipGroup
{
	public final int ship_id;
	public final int destination_planet_id;
	public final int owner_player_id;
	public final int ammount;

	public Position position;

	public ShipGroup(
		int      ship_id,
		int      destination_planet_id,
		int      owner_player_id,
		int      ammount,
		Position position
	)
	{
		this.ship_id               = ship_id;
		this.destination_planet_id = destination_planet_id;
		this.owner_player_id       = owner_player_id;
		this.ammount               = ammount;
		this.position              = position;
	}
}
