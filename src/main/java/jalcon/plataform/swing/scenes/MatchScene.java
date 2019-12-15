package jalcon.plataform.swing.scenes;

import java.awt.*;
import javax.swing.*;

import jalcon.plataform.swing.components.*;

public class MatchScene
extends JPanel
{
	private static final long serialVersionUID = 42l;

	public MatchScene()
	{
		this.setLayout( new BorderLayout() );
		this.setPreferredSize( new Dimension(600, 400) );
		this.add( new SwingGameEngineRenderer(), BorderLayout.CENTER );
	}
}
