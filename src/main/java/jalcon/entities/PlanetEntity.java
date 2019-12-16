package jalcon.entities;

import java.awt.*;

import jalcon.engine.*;
import jalcon.engine.math.*;
import jalcon.models.entities.*;

public class PlanetEntity
extends Planet
implements
	Entity
{
	public PlanetEntity(
		int        planet_id,
		Position   position,
		PlanetType type,
		float      ships_count,
		Player     owner
	)
	{
		super(
			planet_id,
			position,
			type,
			ships_count,
			owner
		);
	}

	@Override
	public void render(Graphics2D g2d)
	{
		g2d.setColor(Color.RED);
		g2d.fillOval(
			(int) ( this.position.x - (this.type.radius / 2) ),
			(int) ( this.position.y - (this.type.radius / 2) ),
			(int) ( this.position.x + (this.type.radius / 2) ),
			(int) ( this.position.y + (this.type.radius / 2) )
		);

		g2d.setColor(Color.BLACK);
		g2d.drawString(
			Float.toString(this.ships_count),
			this.position.x,
			this.position.y
		);
	}

	@Override
	public void update(long delta)
	{
		this.ships_count += ( (float) delta / 1000f ) * this.type.production_per_second;
	}
}
