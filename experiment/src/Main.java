import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {

		ButtonInit framee = new ButtonInit();
		framee.setTitle("We Can Hack This");
		framee.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		framee.setBounds(0, 0, screenSize.width, screenSize.height);
		framee.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		framee.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				framee.handleClosing();
			}
		});

	}

}