//Node class for stack
public class Node{
	DigitalClockView view;
	public int second,minute,hour,dayofweekindex,day,month,year;
	private Node next;
	public Node(DigitalClockView currView){
		view=currView;
	}
	public Node(TheTime myTime){
		second = myTime.getSecond();
		minute = myTime.getMinute();
		hour = myTime.getHour();
		dayofweekindex = myTime.getDayOfWeekIndex();
		day = myTime.getDay();
		month = myTime.getMonth();
		year = myTime.getYear();
	}
	public void setNext(Node n){
		next = n;
	}
	public Node getNext(){
		return next;
	}
	public boolean hasNext(){
		if(next != null) return true;
		else return false;
	}
}
