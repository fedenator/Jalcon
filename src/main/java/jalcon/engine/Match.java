package jalcon.engine;

import java.util.HashMap;
import java.util.Optional;

import jalcon.engine.math.Position;
import jalcon.entities.PlanetEntity;
import jalcon.models.entities.PlanetType;

public class Match {

	private final HashMap<Integer, PlanetEntity> planets;
	private final Universe                       universe;
	
	public Match(
		Universe universe
	) 
	{
		this.universe = universe;
		this.planets = new HashMap<>();
		debug();
	}
	
	// DEBUG (fpalacios): Una forma rapida de spawnear entidades
	private void debug()
	{
		PlanetEntity planet1 = new PlanetEntity(
			this,
			0,
			new Position(100, 100),
			PlanetType.TYPE_0,
			0,
			null
		);
		PlanetEntity planet2 = new PlanetEntity(
			this,
			1,
			new Position(200, 200),
			PlanetType.TYPE_0,
			0,
			null
		);
		
		this.universe.add_entity(planet1);
		this.universe.add_entity(planet2);
		this.planets.put(planet1.planet_id, planet1);
		this.planets.put(planet2.planet_id, planet2);
	}
	

	//FIXME(fpalacios): Hacer esto de una manera que no me haga quierer arrancarme los ojos
	public Optional<PlanetEntity> get_planet_by_id(int planet_id)
	{
		return Optional.ofNullable(this.planets.get(planet_id));
	}
}
