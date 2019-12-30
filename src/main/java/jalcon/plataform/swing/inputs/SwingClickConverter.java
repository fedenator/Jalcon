package jalcon.plataform.swing.inputs;

import java.awt.*;
import java.awt.event.*;

import jalcon.engine.math.*;
import jalcon.engine.events.*;

public class SwingClickConverter
implements
	MouseListener,
	MouseMotionListener
{
	public final int engine_dimensions_width;
	public final int engine_dimensions_height;

	public final Component panel;

	public SwingClickConverter(
		int       engine_dimensions_width,
		int       engine_dimensions_height,
		Component panel
	)
	{
		this.engine_dimensions_width  = engine_dimensions_width;
		this.engine_dimensions_height = engine_dimensions_height;

		this.panel = panel;
	}

	private void scale_point(Position point)
	{
		Dimension panel_dimensions = panel.getSize();

		double x_scale = this.engine_dimensions_width / panel_dimensions.getWidth();
		double y_scale = this.engine_dimensions_height / panel_dimensions.getHeight();

		point.x *= x_scale;
		point.y *= y_scale;
	}

	@Override
	public void mousePressed(MouseEvent event)
	{
		Position pos = new Position( (int) event.getX(), (int) event.getY() );
		this.scale_point(pos);
		MouseDownEvent mouse_down_event = new MouseDownEvent(pos);
		System.out.println(mouse_down_event);
	}
	@Override
	public void mouseReleased(MouseEvent event)
	{
		Position pos = new Position( (int) event.getX(), (int) event.getY() );
		this.scale_point(pos);
		MouseUpEvent mouse_up_event = new MouseUpEvent(pos);
		System.out.println(mouse_up_event);
	}
	@Override
	public void mouseClicked(MouseEvent event)
	{
	}
	@Override
	public void mouseExited(MouseEvent event)
	{
	}
	@Override
	public void mouseEntered(MouseEvent event)
	{
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		Position pos = new Position( (int) event.getX(), (int) event.getY() );
		this.scale_point(pos);
		MouseMoveEvent mouse_move_event = new MouseMoveEvent(pos);
		System.out.println(mouse_move_event);
	}
	@Override
	public void mouseDragged(MouseEvent event)
	{
		Position pos = new Position( (int) event.getX(), (int) event.getY() );
		this.scale_point(pos);
		MouseMoveEvent mouse_move_event = new MouseMoveEvent(pos);
		System.out.println(mouse_move_event);
	}
}
