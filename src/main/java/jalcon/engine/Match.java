package jalcon.engine;

import java.util.*;

import jalcon.entities.*;
import jalcon.models.entities.*;

public class Match
{
	public final HashMap<Integer, PlanetEntity> planets;
	public final HashMap<Integer, Player      > players;
	public final Universe                       universe;

	public Match(
		Universe universe
	)
	{
		this.universe = universe;
		this.planets  = new HashMap<>();
		this.players  = new HashMap<>();
	}

	public Optional<PlanetEntity> get_planet_by_id(int planet_id)
	{
		return Optional.ofNullable(
			this.planets.get(planet_id)
		);
	}

	public Optional<Player> get_player_by_id(int player_id)
	{
		return Optional.ofNullable(
			this.players.get(player_id)
		);
	}

	//TODO(fpalacios): Definir que parametros deberian pasarse en esta notifiacion
	public void on_planet_converted()
	{
		Iterator<PlanetEntity> planet_it = this.planets.values().iterator();

		if ( !planet_it.hasNext() )
		{
			//TODO(fpalacios): Manejar incoherencias
			throw new RuntimeException("WTF: No hay planetas en el game.");
		}

		int potential_winer_player_id = planet_it.next().owner_id;
		boolean player_won = true;

		while ( planet_it.hasNext() )
		{
			PlanetEntity planet = planet_it.next();
			if (planet.owner_id != potential_winer_player_id)
			{
				player_won = false;
			}
		}

		if (player_won)
		{
			this.finish_match(potential_winer_player_id);
		}
	}

	private void finish_match(int winer_player_id)
	{
		System.out.println("Gano el jugador " + winer_player_id);
	}
}
