package jalcon.models.events;

public class SendShipsEvent
{
	public final int player_id;
	public final int source_planet_id;
	public final int destination_player_id;
	public final int ammount;
	
	public SendShipsEvent(
		int player_id,
		int source_planet_id,
		int destination_player_id,
		int ammount
	)
	{
		this.player_id             = player_id;
		this.source_planet_id      = source_planet_id;
		this.destination_player_id = destination_player_id;
		this.ammount               = ammount;
	}
}
