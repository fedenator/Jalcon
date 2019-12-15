package jalcon.models.events;

import java.util.ArrayList;

public class CreateControlGroupEvent
{
	public final int                player_id;
	public final int                control_group_id;
	public final ArrayList<Integer> planets_planet_id;
	
	public CreateControlGroupEvent(
		int                player_id,
		int                control_group_id,
		ArrayList<Integer> planets_planet_id
	)
	{
		this.player_id         = player_id;
		this.control_group_id  = control_group_id;
		this.planets_planet_id = planets_planet_id;
	}
}
