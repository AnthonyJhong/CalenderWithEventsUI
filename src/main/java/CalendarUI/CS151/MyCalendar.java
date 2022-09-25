package CalendarUI.CS151;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * MyCalendar for Calendar project
 * @author AnthonyJhong
 *@version 1.0 2/11/2019
 */

/**
 * The MyCalendar class consists of an ArrayList of Events and the current date that 
 * the user is viewing
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class MyCalendar {
	
	private LocalDate currentViewingDate;
	private ArrayList<Event> allEvents;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Constructs a MyCalendar object that instantiates the date and the ArrayList of events
	 */
	public MyCalendar() {
		currentViewingDate = LocalDate.now();
		allEvents = new ArrayList<Event>();
		listeners = new ArrayList<ChangeListener>();
	}
	/**
	 * this will let all of the ChangeListeners that the calendar has updated the currentViewingDate
	 * @param day
	 */
	public void update(LocalDate day)
	{
		currentViewingDate = day;
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
	/**
    Attach a listener to the Model
    @param c the listener
	 */
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	/**
	 * Sets the current date the user is viewing to the current date
	 */
	public void setDateToCurrent() {
		currentViewingDate = LocalDate.now();
	}
	/**
	 * Returns the current viewing date of the user
	 * @return current viewing date
	 */
	public LocalDate getCurrentViewDate() {
		return currentViewingDate;
	}
	/**
	 * Returns the ArrayList of Events
	 * @return ArrayList of Events
	 */
	public ArrayList<Event> getAllEvents(){
		return allEvents;
	}
	/**
	 * This will change the current viewing date by days
	 * @param i how many days to change the viewing date by
	 */
	public void ascendByDay(int i) {
		currentViewingDate = currentViewingDate.plusDays(i);
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
	/**
	 * Adds an event to ArrayList of Events
	 * @param e Event that user wants to add
	 */
	public void addEvent(Event e) {
		allEvents.add(e);
		Collections.sort(allEvents);
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
	/**
	 * Method will let the user know if there is a recurring event on a certain day
	 * @param day day that the user wants to check for
	 * @return boolean
	 */
	public boolean isEventOnDate(LocalDate day) {
		if(allEvents.isEmpty()) {return false;}
		
		Event tempEvent = allEvents.get(0);
		
		for(int i = 0; i < allEvents.size(); i++) {
			
			tempEvent = allEvents.get(i);
				if(tempEvent.getEventStartDate().equals(day)) {
					return true;
				}
			}
		return false;
	}
	/**
	 * Method will let the you know if a certain time and date will overlap any event in the event list
	 * @param day day that the user wants to check for
	 * @param time the time that needs to be cross checked for overlap
	 * @return boolean boolean true (the to be created event overlaps) 
	 * 		   false (The to be created event does not overlap)
	 */
	public boolean eventOverlap(LocalDate day, TimeInterval time) {
		if(allEvents.isEmpty()) {return false;}
		
		Event tempEvent = allEvents.get(0);
		
		for(int i = 0; i < allEvents.size(); i++) {
			
			tempEvent = allEvents.get(i);
			if( tempEvent.getScheduled().isOverlapping(time) &&
					allEvents.get(i).getEventStartDate().equals(day)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Method will let you know your event list contains an event with the name specified
	 * @param eventName name of the event you are looking for
	 * @return boolean (True event list contains event, False the event list does not)
	 */
	public boolean nameExists(String eventName) {
		if(allEvents.size() == 0)
			return false;
		for(int i = 0; i < allEvents.size(); i++) {
			if(allEvents.get(i).getName().toLowerCase().equals(eventName.toLowerCase()))
				return true;
		}
		return false;
	}
	/**
	 * Returns all of the events on a given day
	 * @param day the day that you want to view
	 * @return ArrayList<Event> all of the events on the inputted day
	 */
	public ArrayList<Event> getEventsOnDay(LocalDate day){
		
		ArrayList<Event> eventsOnDay = new ArrayList<Event>();
		if(allEvents.isEmpty()) {return eventsOnDay;}

		Event tempEvent = allEvents.get(0);

		for(int i = 0; i < allEvents.size(); i++) {

			tempEvent = allEvents.get(i);
			if(tempEvent.getEventStartDate().equals(day)) {
				eventsOnDay.add(tempEvent);
			}
		}
		return eventsOnDay;
	}
	/**
	 * This will take all the events in the class and print them out into output.txt
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void eventsToText() throws FileNotFoundException, UnsupportedEncodingException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

		PrintWriter out = new PrintWriter(new File("events.txt"), "UTF-8");
		if(allEvents.size() != 0) {
			for(int i = 0; i < allEvents.size(); i++) {
					out.println(allEvents.get(i).getName());
					out.print(formatter.format(allEvents.get(i).getEventStartDate()) + " ");
					out.print(allEvents.get(i).getScheduled().getStartTime().toString() + " ");
					out.println(allEvents.get(i).getScheduled().getEndTime().toString() + " ");	
			}
		}
		out.close();
	}
}