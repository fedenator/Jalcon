package jalcon.models.entities;

import jalcon.engine.math.*;

public class Planet
{
	public final int        planet_id;
	public final PlanetType type;
	
	public Position position;
	public float    ships_count;
	public Player   owner;
	
	public Planet(
		int        planet_id,
		Position   position,
		PlanetType type,
		float      ships_count,
		Player     owner
	)
	{
		this.planet_id   = planet_id;
		this.position    = position;
		this.type        = type;
		this.ships_count = ships_count;
		this.owner       = owner;
	}
}