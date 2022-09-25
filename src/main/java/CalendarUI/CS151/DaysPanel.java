package CalendarUI.CS151;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * DaysPanel is the panel that has a gridlayout, it will output all of the days in the month using buttons
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class DaysPanel extends JPanel implements ChangeListener{
	private static final long serialVersionUID = 1L;
	private MyCalendar calendar;
	private LocalDate currentDate;
	private JButton[][] buttons;
	JPanel [][]placeHolder;

	
	/**
	 * Constructor of the days panel that will contain buttons for each day of the month
	 * @param cal
	 */
	public DaysPanel(MyCalendar cal) {
		calendar = cal;
		currentDate = calendar.getCurrentViewDate();
		
		//lets try icon
		
		setLayout(new GridLayout(7, 7, 5,5));
		setBackground(Color.WHITE);
		
		setPreferredSize(new Dimension(200, 300));
		placeHolder = new JPanel[7][7];
		buttons = new JButton[6][7]; 
		
		
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new JButton();
			}
		}
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				placeHolder[i][j] = new JPanel();
				placeHolder[i][j].setSize(1, 1);
				placeHolder[i][j].setBackground(Color.WHITE);
				add(placeHolder[i][j]);
			}
			
		}
		placeHolder[0][0].add(new JLabel("Su"));
		placeHolder[0][1].add(new JLabel("Mo"));
		placeHolder[0][2].add(new JLabel("Tu"));
		placeHolder[0][3].add(new JLabel("We"));
		placeHolder[0][4].add(new JLabel("Th"));
		placeHolder[0][5].add(new JLabel("Fr"));
		placeHolder[0][6].add(new JLabel("Sa"));
		
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length; j++) {
				placeHolder[i+1][j].add(buttons[i][j]);
			}
		}
		updateButtons();
		setVisible(true);
		
	}
	/**
	 * Updates the button after a mutator is MClanedar is called
	 */
	private void updateButtons() {
		
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length;j++) {
			    for( ActionListener al : buttons[i][j].getActionListeners() ) {
			    	if(buttons[i][j].getActionListeners().length != 0)
			    		buttons[i][j].removeActionListener( al );
			    }
			}
		}

		LocalDate firstDay = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
		int days = currentDate.lengthOfMonth();
		DayOfWeek numOfDate = firstDay.getDayOfWeek();
		int dayOfWeekVal = numOfDate.getValue();
		int counter = 0;
		Border emptyBorder = BorderFactory.createEmptyBorder();

		
		if(dayOfWeekVal != 7) {
			for(int i = 0; i < dayOfWeekVal; i++) {
				buttons[0][i].setText("");
				buttons[0][i].setBackground(Color.WHITE);
				buttons[0][i].setOpaque(true);
				buttons[0][counter%7].setBorder(emptyBorder);
				counter++;
			}
		}
		
		
		int placeHolderIndex = 0;
	
		for(int i = 1; i <= days; i ++) {
			if(counter%7 == 0 &&i !=1)
				placeHolderIndex +=1;
			
			if(currentDate.getDayOfMonth() == i) {
				buttons[placeHolderIndex][counter%7].setBackground(Color.WHITE);
				buttons[placeHolderIndex][counter%7].setBorder(new LineBorder(Color.BLACK));
				buttons[placeHolderIndex][counter%7].setPreferredSize(new Dimension(20,17));
			}
			else {
				buttons[placeHolderIndex][counter%7].setBackground(Color.WHITE);
				buttons[placeHolderIndex][counter%7].setPreferredSize(new Dimension(20,17));
				buttons[placeHolderIndex][counter%7].setBorder(emptyBorder);
			}
			buttons[placeHolderIndex][counter%7].setText(String.valueOf(i));
			
			JButton current = buttons[placeHolderIndex][counter%7];
			current.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LocalDate updatedDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), Integer.parseInt(current.getText()));
					calendar.update(updatedDate);
					current.setBorder(new LineBorder(Color.BLACK));
				}
			});
			counter ++;
		}
		
		if(counter % 7 != 0) {
			for(int i = counter % 7; i < 7; i++) {
				buttons[placeHolderIndex][i].setText("");
				buttons[placeHolderIndex][i].setBackground(Color.WHITE);
				buttons[placeHolderIndex][i].setOpaque(true);
				buttons[placeHolderIndex][i].setBorder(emptyBorder);
			}
			if(placeHolderIndex == 4) {
				for(int i = 0; i < 7; i++) {
					buttons[5][i].setText("");
					buttons[5][i].setBackground(Color.WHITE);
					buttons[5][i].setOpaque(true);
					buttons[5][i].setBorder(emptyBorder);
				}
			}
		}
	}
	/**
	 * returns the current day of the DaysPAnel
	 * @return LocalDate 
	 */
	public LocalDate getCurrentDate() {
		return currentDate;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		currentDate = calendar.getCurrentViewDate();
		updateButtons();
		this.revalidate();
	}
}
