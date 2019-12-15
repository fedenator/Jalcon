package jalcon.plataform.swing.stages;

import java.awt.*;
import javax.swing.*;

import jalcon.plataform.swing.scenes.*;

public class MainWindow
extends JFrame
{
	private static final long serialVersionUID = 42l;

	private JPanel current_scene;

	public MainWindow()
	{
		super("Jalcon");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout( new BorderLayout() );

		this.setVisible(true);

		this.goto_main_menu();
	}

	public void goto_main_menu()
	{
		this.change_scene( new MainMenu(this) );
	}

	public void goto_game()
	{
		this.change_scene( new MatchScene() );
	}

	private void change_scene(JPanel new_scene)
	{
		Container content_pane = this.getContentPane();

		if (this.current_scene != null)
		{
			content_pane.remove(this.current_scene);
		}

		this.current_scene = new_scene;
		content_pane.add(new_scene, BorderLayout.CENTER);

		this.pack();
	}
}
