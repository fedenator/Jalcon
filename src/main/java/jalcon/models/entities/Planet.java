package jalcon.models.entities;

import jalcon.math.*;

public class Planet
{
	public final int        planet_id;
	public final PlanetType type;

	public Position position;
	public float    ships_count;
	public int      owner_id;

	public Planet(
		int        planet_id,
		Position   position,
		PlanetType type,
		float      ships_count,
		int        owner_id
	)
	{
		this.planet_id   = planet_id;
		this.position    = position;
		this.type        = type;
		this.ships_count = ships_count;
		this.owner_id    = owner_id;
	}
}
