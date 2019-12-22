package jalcon.models.entities;

import java.awt.*;
import java.util.*;

public class Player
{
	public final int                                    player_id;
	public final ArrayList<Integer>                     active_selection;
	public final HashMap< Integer, ArrayList<Integer> > control_groups;
	public final Color                                  player_color;

	//TODO(fpalacios): Cambiar este nombre por algo más reprecentativo
	public float slider;

	public Player(int player_id, Color player_color)
	{
		this.player_id        = player_id;
		this.player_color     = player_color;
		this.active_selection = new ArrayList<>();
		this.control_groups   = new HashMap<>(10);
	}
}
