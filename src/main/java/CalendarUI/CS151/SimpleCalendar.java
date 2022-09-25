package CalendarUI.CS151;

import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *MyCalendarTester
 *@author AnthonyJhong
 *@version 1.0 2/11/2019
 * */
 
/**
 * SimplerCalendar tests my Event, TimeInterval, and MyCalendar Classes through GUI
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class SimpleCalendar {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		MyCalendar cal = new MyCalendar();
		System.out.println();
		
		DateTimeFormatter dateFormatterforRead = DateTimeFormatter.ofPattern("M/d/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
		
		Scanner in = new Scanner(new File("C:\\Users\\antho\\IdeaProjects\\CalendarWithUI\\src\\main\\java\\CalendarUI\\CS151\\events.txt"), "UTF-8");
		
		while(in.hasNext()) {
			
			String name = in.nextLine();
			String dateTime = in.nextLine();
			String []dates = dateTime.split(" ");
			LocalDate tempDate = LocalDate.parse(dates[0], dateFormatterforRead);
			LocalTime tempStart = LocalTime.parse(dates[1], timeFormatter);
			LocalTime tempEnd = LocalTime.parse(dates[2], timeFormatter);
			TimeInterval eventInterval = new TimeInterval(tempStart, tempEnd);
			Event currentEvent = new Event(eventInterval, name, tempDate);
			cal.addEvent(currentEvent);
		}
		System.out.println("Loading is Done! \n");
		
		in.close();
		DaysPanel days = new DaysPanel(cal);
		TextPanel text = new TextPanel(cal);
		HeaderPanel header = new HeaderPanel(cal);
		cal.attach(days);
		cal.attach(text);
		CalendarPanel panel = new CalendarPanel(days);
		cal.attach(panel);
		CalendarFrame frame = new CalendarFrame(panel, text, header);
		
	}
}
