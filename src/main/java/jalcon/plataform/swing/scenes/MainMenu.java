package jalcon.plataform.swing.scenes;

import javax.swing.*;

import jalcon.plataform.swing.stages.*;

public class MainMenu
extends Scene
{
	private static final long serialVersionUID = 42l;

	public MainMenu(MainWindow main_window)
	{
		this.add( new JLabel("Jalcon!") );
		JButton btn_play = new JButton("Play!");
		btn_play.addActionListener( (event) -> {
			main_window.goto_game();
		});
		this.add(btn_play);
	}

	@Override
	public void on_realized()
	{
		//TODO(fpalacios): Implementar el focus grab
	}
}
