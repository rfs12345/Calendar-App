
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

public class ButtonInit extends JFrame implements ActionListener {

	Container container = getContentPane();
	JPanel header = new JPanel();
	JPanel calender = new JPanel();
	JPanel buffer1 = new JPanel();
	JPanel buffer2 = new JPanel();
	JPanel buffer3 = new JPanel();

	JButton[] arr = new JButton[31];
	JButton save = new JButton("Save");
	JTextField textAdd = new JTextField();
	GridBagConstraints c = new GridBagConstraints();

	JLabel bufferE = new JLabel("");
	JLabel Title = new JLabel("Calendar App");
	JLabel Month = new JLabel("March");
	
	// to see if save button was pressed.
	boolean saveButtonP = false;
	// to be implemented so months can be automatically be updated.
	Calendar cal = Calendar.getInstance();
	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	/*
	 * int month = cal.get(Calendar.MONTH); int maxDay =
	 * cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	 */

	ButtonInit() {
		setLocations();
		addToFrame();
		addToAction();

		// creating file/checking if it exists.
		File f = new File("March 2022.txt");
		if (f.exists()) {
			updateButtons("March 2022.txt");
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setLocations() {

		// main body
		container.setSize(new Dimension(150, 150));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		container.setBounds(0, 0, screenSize.width, screenSize.height);
		container.setLayout(new BorderLayout());

		// header JPanel
		header.setAlignmentY(Component.TOP_ALIGNMENT);

		// Adding label to header
		Title.setFont(new Font("Serif", Font.PLAIN, 36));
		header.add(Title);

		// calender JPanel
		calender.setLayout(new GridLayout(10, 3));
		calender.setAlignmentY(Component.BOTTOM_ALIGNMENT);

		// Adding label && aligning it.
		addButtons(calender);

		// Setting fonts and adding text to other labels.
		Month.setFont(new Font("Serif", Font.PLAIN, 36));
		buffer3.setLayout(new BoxLayout(buffer3, BoxLayout.Y_AXIS));
		buffer3.add(Month);
		bufferE.setText("<html>" + "Orange = " + "<br>" + "Current Month" + "</html>");
		bufferE.setFont(new Font("Serif", Font.PLAIN, 36));
		buffer1.add(bufferE);
		buffer3.add(save);

	}

	public void addButtons(JPanel p) {
		int i;
		for (i = 0; i < 30; i++) {
			arr[i] = new JButton();
			arr[i].setText("" + (i + 1));
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridx = 1;
			c.gridy = 0;
			p.add(arr[i]);
			if (i + 1 == dayOfMonth) {
				arr[i].setBackground(Color.ORANGE);
			}
		}
		arr[i] = new JButton();
		arr[i].setText("" + (i + 1));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		p.add(arr[i]);
		if (i + 1 == dayOfMonth) {
			arr[i].setBackground(Color.ORANGE);
		}
	}

	public void addToFrame() {
		container.add(header, BorderLayout.PAGE_START);
		container.add(buffer3, BorderLayout.WEST);
		container.add(calender, BorderLayout.CENTER);
		container.add(buffer1, BorderLayout.EAST);
		container.add(buffer2, BorderLayout.SOUTH);
	}

	public void addToAction() {
		for (int i = 0; i < 31; i++) {
			arr[i].addActionListener(this);
		}
		save.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			saveButtonP = true;
			File f = new File("March 2022.txt");
			if (f.exists()) {
				editButtons("March 2022.txt");
			} else {
				try {
					f.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else {
			for (int i = 0; i < 31; i++) {
				if (e.getSource() == arr[i]) {
					changeText(arr[i], i);
					break;
				}
			}
		}
	}

	public void changeText(JButton b, int i) {
		String result = JOptionPane.showInputDialog(container,
				"Enter Activity Information: \n(Don't type anything and press 'Ok' to Erase Previous Activities)");
		if (result == null) {
			return;
		}
		b.setText("<html>" + "<center>" + (i + 1) + "</center>" + "<br>" + result + "</html>");
	}

	public void updateButtons(String s) {
		Path path = Paths.get(s);
		Scanner sc;
		try {
			sc = new Scanner(new File(path.toString()));
			sc.useDelimiter(","); // sets the delimiter pattern
			while (sc.hasNext()) // returns a boolean value
			{
				for (int i = 0; i < 31; i++) {
					arr[i].setText(sc.next());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editButtons(String s) {
		FileWriter writer;
		try {
			writer = new FileWriter(s);
			for (int i = 0; i < 31; i++) {
				writer.write(arr[i].getText() + "\t" + ",");
			}
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void windowClosing(WindowEvent e) {
		handleClosing();
	}

	public void handleClosing() {
		//int answer = showWarningMessage();
		if (saveButtonP == false) {
			int answer = showWarningMessage();
			switch (answer) {
			case JOptionPane.YES_OPTION:
				editButtons("March 2022.txt");
				dispose();
				break;

			case JOptionPane.NO_OPTION:
				dispose();
				break;

			case JOptionPane.CANCEL_OPTION:
				break;
			}
		} else {
			dispose();
		}
	}

	private int showWarningMessage() {
		// TODO Auto-generated method stub
		String[] buttonLabels = new String[] { "Yes", "No", "Cancel" };
		String defaultOption = buttonLabels[0];
		Icon icon = null;

		return JOptionPane.showOptionDialog(this, "Do you want to save before exiting?", "Warning",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, icon, buttonLabels, defaultOption);
	}
}
