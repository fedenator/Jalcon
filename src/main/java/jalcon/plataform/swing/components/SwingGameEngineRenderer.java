package jalcon.plataform.swing.components;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

import jalcon.engine.*;
import jalcon.plataform.*;

public class SwingGameEngineRenderer
extends JPanel
implements
	PlataformRenderer
{
	private static final long serialVersionUID = 42l;

	private BufferedImage buffer;

	public SwingGameEngineRenderer()
	{
		new GameEngine(this);
	}

	@Override
	public void render(BufferedImage buffer)
	{
		this.buffer = buffer;
		this.paintImmediately( this.getBounds() );
	}

	@Override
	public void paintComponent(Graphics g)
	{
		// Es posible que Swing quiera pintar la ventana antes que el GameEngine
		// haya podio terminar el pimrer frame
		if (this.buffer == null)
		{
			return;
		}

		Rectangle this_bounds = this.getBounds();
		
		g.drawImage(
			this.buffer,
			this_bounds.x,
			this_bounds.y,
			this_bounds.x + this_bounds.width,
			this_bounds.y + this_bounds.height,
			0,
			0,
			this.buffer.getWidth(),
			this.buffer.getHeight(),
			null
		);
	}
}
