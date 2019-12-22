package jalcon.plataform.swing.scenes;

import java.awt.BorderLayout;
import java.awt.Dimension;

import jalcon.engine.Match;
import jalcon.plataform.swing.components.SwingGameEngineRenderer;

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
	}

	@Override
	public void on_realized()
	{
		this.engine_renderer.requestFocusInWindow();
	}
}
