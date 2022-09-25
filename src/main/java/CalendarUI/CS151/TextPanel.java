package CalendarUI.CS151;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TextPanel will create a JTextArea that outputs the events of the selected day
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class TextPanel extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private MyCalendar calendar;
	private ArrayList<Event> allEventsOnCurrent;
	private LocalDate currentViewDate;
	private JTextArea text;
	private String formattedText;
	
	/**
	 * Creates a TextPanel that consists of all events on the selected day
	 * @param cal
	 */
	public TextPanel(MyCalendar cal){
		
		setPreferredSize(new Dimension(600,200));
		calendar = cal;
		text = new JTextArea();
		text.setPreferredSize(new Dimension(600,200));
		currentViewDate = calendar.getCurrentViewDate();
		allEventsOnCurrent = calendar.getEventsOnDay(currentViewDate);
		setFromattedText();
		text.setText(formattedText);
		add(text);
		
		setVisible(true);
		
	}
	/**
	 * This will format the text
	 */
	public void setFromattedText() {
		
		formattedText="";
		formattedText += currentViewDate.getDayOfWeek();
		formattedText = formattedText.substring(0, 1) + formattedText.substring(1).toLowerCase() + " ";
		formattedText += currentViewDate.getMonthValue()+"/"+currentViewDate.getDayOfMonth()+ "\n";
		if(allEventsOnCurrent.size() > 0) {
			for(int i = 0; i < allEventsOnCurrent.size(); i++) {
				formattedText += allEventsOnCurrent.get(i).toString() + "\n";
			}
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		currentViewDate = calendar.getCurrentViewDate();
		allEventsOnCurrent = calendar.getEventsOnDay(currentViewDate);
		setFromattedText();
		text.setText(formattedText);
		
	}

}
