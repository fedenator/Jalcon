package jalcon.plataform.swing.scenes;

import java.awt.*;
import java.awt.Dimension;

import jalcon.math.*;
import jalcon.engine.*;
import jalcon.entities.*;
import jalcon.models.entities.*;
import jalcon.plataform.swing.components.*;

public class MatchScene
extends Scene
{
	private static final long serialVersionUID = 42l;

	private final SwingGameEngineRenderer engine_renderer;
	private final Match                   match;

	public MatchScene()
	{
		this.setLayout( new BorderLayout() );
		this.setPreferredSize( new Dimension(600, 400) );
		this.engine_renderer = new SwingGameEngineRenderer();
		this.add(engine_renderer, BorderLayout.CENTER);
		this.match = new Match(this.engine_renderer.game_engine.universe);

		this.debug();
	}

	// DEBUG (fpalacios): Una forma rapida de spawnear entidades
	private void debug()
	{
		Player player0 = new Player(0, Color.RED );
		Player player1 = new Player(1, Color.BLUE);

		this.match.players.put(player0.player_id, player0);
		this.match.players.put(player1.player_id, player1);

		PlanetEntity planet0 = new PlanetEntity(
			this.match,
			0,
			new Position(100, 100),
			PlanetType.TYPE_0,
			0,
			player0.player_id
		);
		PlanetEntity planet1 = new PlanetEntity(
			this.match,
			1,
			new Position(200, 200),
			PlanetType.TYPE_0,
			0,
			player1.player_id
		);

		this.match.universe.add_entity(planet0);
		this.match.universe.add_entity(planet1);
		this.match.planets.put(planet0.planet_id, planet0);
		this.match.planets.put(planet1.planet_id, planet1);
	}

	@Override
	public void on_realized()
	{
		this.engine_renderer.requestFocusInWindow();
	}
}
