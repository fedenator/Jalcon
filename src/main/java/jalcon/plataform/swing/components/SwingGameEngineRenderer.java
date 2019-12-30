package jalcon.plataform.swing.components;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

import jalcon.engine.*;
import jalcon.plataform.*;
import jalcon.models.events.*;
import jalcon.plataform.swing.inputs.*;

public class SwingGameEngineRenderer
extends JPanel
implements
	PlataformRenderer,
	KeyListener
{
	private static final long serialVersionUID = 42l;

	public final GameEngine game_engine;

	private BufferedImage buffer;

	public SwingGameEngineRenderer()
	{
		SwingClickConverter click_converter = new SwingClickConverter(600, 400, this);
		this.game_engine = new GameEngine(this);
		this.addKeyListener(this);
		this.addMouseListener(click_converter);
		this.addMouseMotionListener(click_converter);
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

	@Override
	public void keyTyped(KeyEvent event)
	{
		//DEBUG(fpalacios): Una forma rapida de probar pushear evenetos
		if (Character.toLowerCase(event.getKeyChar()) == 'f')
		{
			this.game_engine.add_event(
				new SendShipsEvent(
					0,
					0,
					1,
					10
				)
			);
		}
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
	}
	@Override
	public void keyReleased(KeyEvent event)
	{
	}
}
