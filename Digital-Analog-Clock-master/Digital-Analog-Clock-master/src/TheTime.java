import java.awt.event.*;
//Model
public class TheTime {
	private int second;
	private int minute;
	private int hour;
	private String[] dayofweek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	private int dayofweekindex;
	private int day;
	private int month;
	private int year;
	
    public Counter clockTick;
    
    private ActionListener onTick = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	        if(second >= 0 && second < 59){
	        	second++;
	        }
	        else{
	        	second = 0;
	        	minute++;
	        }
	        if(!(minute >= 0 && minute < 59)){
	        	hour++;
	        	minute = 0;
	        }
	        if(hour > 24){
	        	hour = 1;
	        	day++;
	        }
	        if(day > 31){
	        	month ++;
	        	day = 1;
	        }
	        if(month > 12){
	        	year++;
	        	month = 1;
	        }
	        if(dayofweekindex>=7){
	        	dayofweekindex = 0;
	        }
	    }
	};
    
	public TheTime() {
		
		second = 0;
		minute = 0;
		hour = 0;
		dayofweekindex = 0;
		day = 0;
		month = 0;
		year = 0;

		clockTick = new Counter(onTick);
		clockTick.start();
	}
    

	
	// get/set methods go here
    public int getSecond(){
    	return second;
    }
    public int getMinute(){
    	return minute;
    }
    public int getHour(){
    	return hour+1;
    }
    public int getDayOfWeekIndex(){
    	return dayofweekindex;
    }
    public String getDayOfWeek(){
    	return dayofweek[dayofweekindex];
    }
    public int getDay(){
    	return day+1;
    }
    public int getMonth(){
    	return month+1;
    }
    public int getYear(){
    	return year;
    }
    public int setSecond(int s){
    	second = s;
    	return second;
    }
    public int setMinute(int m){
    	minute = m;
    	return minute;
    }
    public int setHour(int h){
    	hour = h-1;
    	return hour;
    }
    public int setDayOfWeek(int d){
    	dayofweekindex = d-1;
    	return dayofweekindex;
    }
    public String setDayOfWeek(String d){
    	for(int i = 0; i < dayofweek.length;i++){
    		if(dayofweek[i] == d){
    			dayofweekindex = i;
    			break;
    		}
    	}
    	return dayofweek[dayofweekindex];
    }
    public int setDay(int d){
    	day = d-1;
    	return day;
    }
    public int setMonth(int m){
    	month = m-1;
    	return month;
    }
    public int setYear(int y){
    	year = y;
    	return year;
    }
	
}