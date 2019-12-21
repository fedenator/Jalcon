package jalcon.models.entities;

import java.util.*;

public class Player
{
	public final int                                    player_id;
	public final ArrayList<Integer>                     active_selection;
	public final HashMap< Integer, ArrayList<Integer> > control_groups;

	//TODO(fpalacios): Cambiar este nombre por algo más reprecentativo
	public float slider;

	public Player(int player_id)
	{
		this.player_id        = player_id;
		this.active_selection = new ArrayList<>();
		this.control_groups   = new HashMap<>(10);

	}
}
