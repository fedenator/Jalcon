package jalcon.models.events;

public class ChangeSliderValueEvent
extends Event
{
	// TODO (fpalacios): sos un puto mario, cambia el nombre de esta clase.
	public final int   player_id;
	public final float value;

	public ChangeSliderValueEvent(
		int   player_id,
		float value
	)
	{
		this.player_id = player_id;
		this.value     = value;
	}
}
