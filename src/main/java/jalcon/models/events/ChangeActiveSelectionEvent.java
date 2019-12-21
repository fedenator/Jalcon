package jalcon.models.events;

import java.util.ArrayList;

public class ChangeActiveSelectionEvent
extends Event
{
	public final int                player_id;
	public final ArrayList<Integer> planets_planet_id;

	public ChangeActiveSelectionEvent(
		int                player_id,
		ArrayList<Integer> planets_planet_id
	)
	{
		this.player_id         = player_id;
		this.planets_planet_id = planets_planet_id;
	}
}
