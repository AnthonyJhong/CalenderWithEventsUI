package CalendarUI.CS151;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
/**
 * HEaderPanel creates buttons that allows the user to traverses the calendar,
 * a create button, and a quit button that will exit the program and enter all of
 * the events to a txt file
 * @author AnthonyJhong
 *
 */
public class HeaderPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MyCalendar calendar;
	/**
	 * Constructor of HeaderPanel that will Create a header panel and add it to the calendar frame
	 * @param cal a MyCalendar instance that will be used to mutate the calendar
	 */
	public HeaderPanel(MyCalendar cal) {
		calendar = cal;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JButton pastDay = new JButton("<");
		pastDay.setBackground(Color.GREEN);
		pastDay.setOpaque(true);
		pastDay.setBorderPainted(false);
		pastDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				calendar.ascendByDay(-1);
			}
			
		});
		add(pastDay);

		JButton nextDay = new JButton(">");
		nextDay.setBackground(Color.GREEN);
		nextDay.setOpaque(true);
		nextDay.setBorderPainted(false);
		nextDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				calendar.ascendByDay(1);
			}
			
		});
		add(nextDay);
		
		JButton create = new JButton("Create");
		create.setBackground(Color.RED);
		create.setOpaque(true);
		create.setBorderPainted(false);
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CreateFrame(calendar);
			}
			
		});
		add(create);
		
		JButton quit = new JButton("Quit");
		quit.setBackground(Color.WHITE);
		quit.setOpaque(true);
		quit.setBorderPainted(false);
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					calendar.eventsToText();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
			
		});
		add(quit);
		
		
		
		setVisible(true);
		
	}
	
}
