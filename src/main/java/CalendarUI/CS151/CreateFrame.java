package CalendarUI.CS151;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
/**
 * When the user hits the create button this frame will be constructed allwoing the user to enter 
 * the name of the event, date, and time interval of the event
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */
public class CreateFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private MyCalendar calendar;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yy");
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
	/**
	 * Creates a Create frame when the create button is pressed
	 * @param cal
	 */
	public CreateFrame(MyCalendar cal) {
		calendar = cal;

		setSize(600, 50);
		
		this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JTextField eventName = new JTextField("Untitled Event");
		
		add(eventName);
		
		JTextField date = new JTextField("MM/DD/YY");
		
		add(date);
		
		JTextField start = new JTextField("hh:mm");
		add(start);
		
		JLabel to = new JLabel("to");
		add(to);
		
		JTextField end = new JTextField("hh:mm");
		add(end);
		
		setVisible(true);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LocalTime s = LocalTime.parse(start.getText(), timeFormatter);
				LocalTime endTime = LocalTime.parse(end.getText(), timeFormatter);
				TimeInterval tempInterval = new TimeInterval(s,endTime);
				Event tempEvent;
				
				if(date.getText().equals("MM/DD/YY")) {
					tempEvent = new Event(tempInterval, eventName.getText(), calendar.getCurrentViewDate());
				}
				else {
					LocalDate tempDate = LocalDate.parse(date.getText(), dateFormatter);
					tempEvent = new Event(tempInterval, eventName.getText(), tempDate);
				}
				
				if(calendar.eventOverlap(tempEvent.getEventStartDate(), tempEvent.getScheduled())) {
					new ErrorFrame();
				}
				else {
					calendar.addEvent(tempEvent);
					dispose();
				}
			}
			
		});
		add(save);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
