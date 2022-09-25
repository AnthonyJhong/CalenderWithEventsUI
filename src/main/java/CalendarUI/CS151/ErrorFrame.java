package CalendarUI.CS151;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Error frame will appear of the user inputs an event that overlaps with another event
 * @author AnthonyJhong
 *
 */

public class ErrorFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of error frame that will create an error frame when called
	 */
	public ErrorFrame() {
		setSize(new Dimension(600,100));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JLabel error = new JLabel("There is is time conflict with the event you are trying to add, please modify!");
		error.setForeground(Color.RED);
		add(error);
		
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		add(ok);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
}
