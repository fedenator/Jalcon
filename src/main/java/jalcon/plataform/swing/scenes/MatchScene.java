package jalcon.plataform.swing.scenes;

import java.awt.*;

import jalcon.plataform.swing.components.*;

public class MatchScene
extends Scene
{
	private final SwingGameEngineRenderer engine_renderer;
	private static final long serialVersionUID = 42l;

	public MatchScene()
	{
		this.setLayout( new BorderLayout() );
		this.setPreferredSize( new Dimension(600, 400) );
		this.engine_renderer = new SwingGameEngineRenderer();
		this.add(engine_renderer, BorderLayout.CENTER);
	}

	@Override
	public void on_realized()
	{
		this.engine_renderer.requestFocusInWindow();
	}
}
