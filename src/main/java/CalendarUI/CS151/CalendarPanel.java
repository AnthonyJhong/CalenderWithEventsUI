package CalendarUI.CS151;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Calendar panel will contain all for the components of the calendar including the days panel
 * and the month header
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class CalendarPanel extends JPanel implements ChangeListener{
	
	
	private static final long serialVersionUID = 1L;
	
	private DaysPanel days;
	private JLabel area;
	String []months = {"January", "Febuary", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December"};
	/**
	 * Creates a panel for the calendar
	 * @param day
	 */
	public CalendarPanel(DaysPanel day) {
		days = day;
		
		setLayout(new BorderLayout());	
		setBackground(Color.WHITE);
		setSize(500, 500);
		
		area = new JLabel(months[days.getCurrentDate().getMonthValue()-1]+ " " + String.valueOf(days.getCurrentDate().getYear()));
		this.add(area, BorderLayout.NORTH);
		this.add(days, BorderLayout.WEST);
		setVisible(true);
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		area.setText(months[days.getCurrentDate().getMonthValue()-1]+ " " + String.valueOf(days.getCurrentDate().getYear()));
		
	}

}
