package CalendarUI.CS151;

import java.time.LocalDate;

/**
 * Event class for Calendar
 * @author AnthonyJhong
 *@version 1.0 2/11/2019
 */


/**
 * Event class allows users to create Events that consist of a TimeInterval, a name, and a date
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 *
 */
public class Event implements Comparable<Event> {
	
	private TimeInterval scheduledTime;
	private String name;
	private LocalDate eventDate;
	
	/**
	 * Constructs an Event with values for TimeInterval, name, start date, end date, and recurring days
	 * @param t Time interval
	 * @param n name
	 * @param sDate start date
	 */
	public Event(TimeInterval t,String n, LocalDate sDate){
		scheduledTime = t;
		name = n;
		eventDate = sDate;
	}
	/**
	 * Sets the value of the name for the Event
	 * @param n value for name
	 */
	public void setName(String n) {
		name = n;
	}
	/**
	 * Sets the TimeInterval for the Event
	 * @param t value of the TimeInterval
	 */
	public void setScheduled(TimeInterval t) {
		scheduledTime = t;
	}
	/**
	 * Sets the date of the event
	 * @param d value of the date of the event
	 */
	public void setEventDate(LocalDate d) {
		eventDate = d;
	}
	/**
	 * Returns the name of the event
	 * @return name of the event
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the TimeInterval of the Event
	 * @return TimeInterval of Event
	 */
	public TimeInterval getScheduled() {
		return scheduledTime;
	}
	/**
	 * Returns the date of the event
	 * @return date of event
	 */
	public LocalDate getEventStartDate() {
		return eventDate;
	}

	/**
	 * Compares events to see if they are in the correct order
	 * @param e an event that will be compared with the current event
	 * @return an integer value that tells you if the events are in the correct order
	 */
	@Override
	public int compareTo(Event e) {
		if(eventDate.compareTo(e.getEventStartDate()) == 0) {
			return scheduledTime.compareTo(e.getScheduled());
		}
		else {
			return eventDate.compareTo(e.getEventStartDate());
		}
	}
	/**
	 * Converts the contents of the Event to a string
	 */
	public String toString() {
		return scheduledTime.intervalToString() + " " + name;
	}
}