package jalcon.plataform.swing;

import java.awt.*;

import jalcon.plataform.swing.stages.MainWindow;

public class SwingApplication
{
	public SwingApplication()
	{
		EventQueue.invokeLater( () -> {
			new MainWindow();
		});
	}
}
