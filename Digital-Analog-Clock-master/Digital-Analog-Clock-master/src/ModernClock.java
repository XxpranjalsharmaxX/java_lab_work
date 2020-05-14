import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Controller
public class ModernClock extends DigitalClockView{
	TheTime time = new TheTime();
	Font timeFont = new Font("Palatino", Font.BOLD,  72);
	private String currentTime;
	private String currentDate;
	
	private ActionListener onTick = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	    	currentTime = time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
			currentDate = time.getMonth() + "/" + time.getDay() + "/" + time.getYear();
	    }
	};
	
	public ModernClock(TheTime myTime){
		time = myTime;
		time.clockTick.addActionListener(onTick);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		String outString = currentTime + "  " + currentDate;
		g.clearRect(0, 0, getWidth(), getHeight());
		g2.setFont(timeFont);
		g2.drawString(outString,getWidth()/2 - (outString.length()*(timeFont.getSize()/4)),getHeight()/2 + 10);
	}
}
