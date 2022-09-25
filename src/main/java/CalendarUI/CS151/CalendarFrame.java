package CalendarUI.CS151;

import javax.swing.*;
import java.awt.*;

/**
 * CalendarFrame will contain all of the components that makes up the GUI
 * this includes the header buttons, the text panel and the CalendarPanel
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class CalendarFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private CalendarPanel calendar;
	private TextPanel eventsOnDay;
	private HeaderPanel header;
	
	/**
	 * Creates a a frame for all of the components of the calendar
	 * @param p
	 * @param t
	 * @param hp
	 */
	public CalendarFrame(CalendarPanel p, TextPanel t, HeaderPanel hp) {
		
		calendar = p;
		eventsOnDay = t;
		header = hp;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(900,300));
		setBackground(Color.WHITE);
		
		add(calendar, BorderLayout.WEST);
		add(eventsOnDay, BorderLayout.CENTER);
		add(header, BorderLayout.NORTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

}
