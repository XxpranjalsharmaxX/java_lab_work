import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;
public class DigitalClock{
	public static DigitalClockView myClock;
	
	//Event Listener for Draw (since changing the face requires redrawing in the parent class)
	private static ActionListener onTick = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	    	myClock.draw();
	    }
	};
	
	public static void main(String argv[]){
		TheTime time = new TheTime();
		time.clockTick.addActionListener(onTick);
		myClock = new AnalogClock(time);
		JFrame f= new JFrame("Digitial Clock: Design Patterns implementation");
		ClockFactory(time,myClock,f);
		f.pack();
		f.setVisible(true);

		Calendar rightNow = Calendar.getInstance();
		time.setHour(rightNow.get(Calendar.HOUR_OF_DAY)%12);
		time.setMinute(rightNow.get(Calendar.MINUTE));
		time.setSecond(rightNow.get(Calendar.SECOND));
		time.setDayOfWeek(rightNow.get(Calendar.DAY_OF_WEEK));
		time.setDay(rightNow.get(Calendar.DAY_OF_MONTH));
		time.setMonth(rightNow.get(Calendar.MONTH));
		time.setYear(rightNow.get(Calendar.YEAR));
		
	}
	

	public static void ClockFactory(final TheTime time,final DigitalClockView clock,final JFrame f){
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(myClock);
		myClock.setPreferredSize(new Dimension(800,600));

		//Stacks implemented from stack class 
		final Stack undoStack = new Stack();
		final Stack redoStack = new Stack();

		//These are the actioneventlisteners for the menu items
		final ActionListener undoListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	if(!undoStack.isEmpty()){
		    		Node curr = undoStack.Pop();
			    	if(curr.view != null){
			    		redoStack.Push(new Node(myClock));
			    		myClock = curr.view;
				    	f.getContentPane().removeAll();
						f.add(myClock);
						myClock.setPreferredSize(new Dimension(800, 600));
						f.pack();
						f.setVisible(true);
			    	}
			    	else{
				    	redoStack.Push(new Node(time));
			    		time.setSecond(curr.second);
			    		time.setMinute(curr.minute);
			    		time.setHour(curr.hour);
			    		time.setDay(curr.day);
			    		time.setMonth(curr.month);
			    		time.setYear(curr.year);
			    	}
		    	}
		    }
		};
		ActionListener redoListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	if(!redoStack.isEmpty()){
		    		Node curr = redoStack.Pop();
			    	if(curr.view != null){
				    	undoStack.Push(new Node(myClock));
			    		myClock = curr.view;
				    	f.getContentPane().removeAll();
						f.add(myClock);
						myClock.setPreferredSize(new Dimension(800, 600));
						f.pack();
						f.setVisible(true);
			    	}
			    	else{
				    	undoStack.Push(new Node(time));
			    		time.setSecond(curr.second);
			    		time.setMinute(curr.minute);
			    		time.setHour(curr.hour);
			    		time.setDay(curr.day);
			    		time.setMonth(curr.month);
			    		time.setYear(curr.year);
			    	}
		    	}
		    }
		};
		ActionListener viewAnalogListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	undoStack.Push(new Node(myClock));
		    	redoStack.Clear();
		    	myClock = new AnalogClock(time);
		    	f.getContentPane().removeAll();
				f.add(myClock);
				myClock.setPreferredSize(new Dimension(800, 600));
				f.pack();
				f.setVisible(true);
		    }
		};
		ActionListener viewDigitalListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	undoStack.Push(new Node(myClock));
		    	redoStack.Clear();
		    	myClock = new ModernClock(time);
		    	f.getContentPane().removeAll();
				f.add(myClock);
				myClock.setPreferredSize(new Dimension(800, 600));
				f.pack();
				f.setVisible(true);
		    }
		};
		ActionListener setSecondListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	Object[] possibilities = null;
		    	String s = (String)JOptionPane.showInputDialog(
		    	                    f,
		    	                    "Set Current Seconds Between 0 and 59",
		    	                    "Set Seconds",
		    	                    JOptionPane.PLAIN_MESSAGE,
		    	                    null,
		    	                    possibilities,
		    	                    "0");

		    	//If a string was returned, say so.
		    	
		    	int seconds = Integer.parseInt(s);
		    	
		    	if ((seconds < 60 && seconds > 0)) {
		    	    undoStack.Push(new Node(time));
		    	    redoStack.Clear();
		    	    time.setSecond(seconds);
		    	    return;
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(f,"Invalid Input!");
		    	}
		    }
		};
		ActionListener setMinuteListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	Object[] possibilities = null;
		    	String s = (String)JOptionPane.showInputDialog(
		    	                    f,
		    	                    "Set Current Minutes Between 0 and 59",
		    	                    "Set Minutes",
		    	                    JOptionPane.PLAIN_MESSAGE,
		    	                    null,
		    	                    possibilities,
		    	                    "0");

		    	//If a string was returned, say so.
		    	
		    	int minutes = Integer.parseInt(s);
		    	
		    	if ((minutes < 60 && minutes > 0)) {
		    	    undoStack.Push(new Node(time));
		    	    redoStack.Clear();
		    	    time.setMinute(minutes);
		    	    return;
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(f,"Invalid Input!");
		    	}
		    }
		};
		ActionListener setHourListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	Object[] possibilities = null;
		    	String s = (String)JOptionPane.showInputDialog(
		    	                    f,
		    	                    "Set Current Hour Between 1 and 12",
		    	                    "Set Seconds",
		    	                    JOptionPane.PLAIN_MESSAGE,
		    	                    null,
		    	                    possibilities,
		    	                    "0");

		    	//If a string was returned, say so.
		    	
		    	int hours = Integer.parseInt(s);
		    	
		    	if ((hours <= 12 && hours >= 1)) {
		    	    undoStack.Push(new Node(time));
		    	    redoStack.Clear();
		    	    time.setHour(hours);
		    	    return;
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(f,"Invalid Input!");
		    	}
		    }
		};
		ActionListener setDayListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	Object[] possibilities = null;
		    	String s = (String)JOptionPane.showInputDialog(
		    	                    f,
		    	                    "Set Current Hour Between 1 and 12",
		    	                    "Set Seconds",
		    	                    JOptionPane.PLAIN_MESSAGE,
		    	                    null,
		    	                    possibilities,
		    	                    "0");

		    	//If a string was returned, say so.
		    	
		    	int day = Integer.parseInt(s);
		    	
		    	if ((day <= 30 && day >= 1)) {
		    	    undoStack.Push(new Node(time));
		    	    redoStack.Clear();
		    	    time.setDay(day);
		    	    return;
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(f,"Invalid Input!");
		    	}
		    }
		};
		ActionListener setDayIndexListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	Object[] dayofweek = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		    	String dayofweekindex = (String)JOptionPane.showInputDialog(
		    	                    f,
		    	                    "Set Current Hour Between 1 and 12",
		    	                    "Set Seconds",
		    	                    JOptionPane.PLAIN_MESSAGE,
		    	                    null,
		    	                    dayofweek,
		    	                    "0");

		    	//If a string was returned, say so.

	    	    undoStack.Push(new Node(time));
	    	    redoStack.Clear();
		    	switch (dayofweekindex){
		    	case "Monday":
		    	    time.setDayOfWeek(0);
		    	    break;
		    	case "Tuesday":
		    	    time.setDayOfWeek(1);
		    	    break;
		    	case "Wednesday":
		    	    time.setDayOfWeek(2);
		    	    break;
		    	case "Thursday":
		    	    time.setDayOfWeek(3);
		    	    break;
		    	case "Friday":
		    	    time.setDayOfWeek(4);
		    	    break;
		    	case "Saturday":
		    	    time.setDayOfWeek(5);
		    	    break;
		    	case "Sunday":
		    	    time.setDayOfWeek(6);
		    	    break;
		    	}
		    }
		};
		ActionListener setMonthListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	Object[] possibilities = null;
		    	String s = (String)JOptionPane.showInputDialog(
		    	                    f,
		    	                    "Set Current Hour Between 1 and 12",
		    	                    "Set Seconds",
		    	                    JOptionPane.PLAIN_MESSAGE,
		    	                    null,
		    	                    possibilities,
		    	                    "0");

		    	//If a string was returned, say so.
		    	
		    	int month = Integer.parseInt(s);
		    	
		    	if ((month <= 12 && month >= 1)) {
		    	    undoStack.Push(new Node(time));
		    	    redoStack.Clear();
		    	    time.setMonth(month);
		    	    return;
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(f,"Invalid Input!");
		    	}
		    }
		};
		ActionListener setYearListener = new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	Object[] possibilities = null;
		    	String s = (String)JOptionPane.showInputDialog(
		    	                    f,
		    	                    "Set Current Hour Between 1 and 12",
		    	                    "Set Seconds",
		    	                    JOptionPane.PLAIN_MESSAGE,
		    	                    null,
		    	                    possibilities,
		    	                    "0");

		    	//If a string was returned, say so.
		    	
		    	int year = Integer.parseInt(s);
		    	
		    	if ((year <= 9999 && year >= 0)) {
		    	    undoStack.Push(new Node(time));
		    	    redoStack.Clear();
		    	    time.setYear(year);
		    	    return;
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(f,"Invalid Input!");
		    	}
		    }
		};
		
		//Adding menu bar
		JMenuBar menuBar = new JMenuBar();
		
		//adding JMenu Groups
		JMenu viewController = new JMenu("View");
		JMenu timeController = new JMenu("Set Time");
		JMenu historyController = new JMenu("History");
		
		//menu items for History JMenu
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(undoListener);
		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener(redoListener);

		//menu items for View JMenu
		JMenuItem setViewAnalog = new JMenuItem("Analog");
		setViewAnalog.addActionListener(viewAnalogListener);
		JMenuItem setViewDigital = new JMenuItem("Digital");
		setViewDigital.addActionListener(viewDigitalListener);
		
		//menu items for Set Time JMenu
		JMenuItem setSecond = new JMenuItem("Set Second");
		setSecond.addActionListener(setSecondListener);
		JMenuItem setMinute = new JMenuItem("Set Minute");
		setMinute.addActionListener(setMinuteListener);
		JMenuItem setHour = new JMenuItem("Set Hour");
		setHour.addActionListener(setHourListener);
		JMenuItem setDayIndex = new JMenuItem("Set Day of the Week");
		setDayIndex.addActionListener(setDayIndexListener);
		JMenuItem setDay = new JMenuItem("Set Day");
		setDay.addActionListener(setDayListener);
		JMenuItem setMonth = new JMenuItem("Set Month");
		setMonth.addActionListener(setMonthListener);
		JMenuItem setYear = new JMenuItem("Set Year");
		setYear.addActionListener(setYearListener);
		
		//add menu items for History JMenu
		historyController.add(undo);
		historyController.add(redo);
		
		
		//add menu items for View JMenu
		viewController.add(setViewAnalog);
		viewController.add(setViewDigital);
		
		//add menu items for Set Time JMenu
		timeController.add(setSecond);
		timeController.add(setMinute);
		timeController.add(setHour);
		timeController.add(setDayIndex);
		timeController.add(setDay);
		timeController.add(setMonth);
		timeController.add(setYear);
		
		//add JMenu components to JMenuBar
		menuBar.add(historyController);
		menuBar.add(viewController);
		menuBar.add(timeController);
		
		//sets frame with JMenuBar
		f.setJMenuBar(menuBar);
	}
}
