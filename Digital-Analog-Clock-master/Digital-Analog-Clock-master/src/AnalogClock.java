import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class AnalogClock extends DigitalClockView {
	Font dateFont = new Font("TimesRoman", Font.PLAIN,  22);
	private TheTime time;
	private final double _PI = Math.PI;
	private final int SEC_STEP = 60; // number of points to store (hand positions)
	private final int MIN_STEP = 60;
	private final int HOUR_STEP = 60;

	private final int XIndex = 0;
	private final int YIndex = 1;

	final double[][] secArray = new double[2][SEC_STEP];
	final double[][] minArray = new double[2][MIN_STEP];
	final double[][] hourArray = new double[2][HOUR_STEP];
	// or
	// CPoint ptArray[STEP_SIZE];
	private double secRadius = 250;  //radius of the second hand
	private double minRadius = 240;
	private double hourRadius = 150;

	public AnalogClock(TheTime myTime){
		
		time = myTime;
		//CALCULATES RADIAL POINTS FOR LINES INTO ARRAYS
		for(int i = 0; i < SEC_STEP ; i++){
			double theta = i * (2* _PI) / SEC_STEP;
		    
		    secArray[XIndex][i] = (secRadius * Math.cos(theta));
		    secArray[YIndex][i] = (secRadius * Math.sin(theta)); // for ellipse use a different value for the radius
		}
		for (int i = 0; i < MIN_STEP; i++){
			double theta = i * (2* _PI) / MIN_STEP;
		    
		    minArray[XIndex][i] = (minRadius * Math.cos(theta));
		    minArray[YIndex][i] = (minRadius * Math.sin(theta)); // for ellipse use a different value for the radius
		}
		for (int i = 0; i < HOUR_STEP; i++){
			double theta = i * (2* _PI) / HOUR_STEP;
		    
			hourArray[XIndex][i] = (hourRadius * Math.cos(theta));
			hourArray[YIndex][i] = (hourRadius * Math.sin(theta)); // for ellipse use a different value for the radius
		}
		double[][] secArrayTemp = new double[2][60];
		double[][] minArrayTemp = new double[2][60];
		double[][] hourArrayTemp = new double[2][60];
		for (int i = 0; i < 60; i++){
			if(i<15){
				secArrayTemp[0][i] = secArray[0][i+45];
				minArrayTemp[0][i] = minArray[0][i+45];
				hourArrayTemp[0][i] = hourArray[0][i+45];
				secArrayTemp[1][i] = secArray[1][i+45];
				minArrayTemp[1][i] = minArray[1][i+45];
				hourArrayTemp[1][i] = hourArray[1][i+45];
			}
			else{
				secArrayTemp[0][i] = secArray[0][i-15];
				minArrayTemp[0][i] = minArray[0][i-15];
				hourArrayTemp[0][i] = hourArray[0][i-15];
				secArrayTemp[1][i] = secArray[1][i-15];
				minArrayTemp[1][i] = minArray[1][i-15];
				hourArrayTemp[1][i] = hourArray[1][i-15];
			}
		}
		for (int i = 0; i < 60; i++){
			for (int j = 0; j < 2; j++){
				secArray[j][i] = secArrayTemp[j][i];
				minArray[j][i] = minArrayTemp[j][i];
				hourArray[j][i] = hourArrayTemp[j][i];
			}
		}
	}
	

	@Override
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int height = getHeight();
		int width = getWidth();
		int cx = width/2;
		int cy = height/2;
		g2.setTransform(AffineTransform.getTranslateInstance(cx, cy));	
		g2.clearRect(-width/2, -height/2, width, height);
		g2.setStroke(new BasicStroke(2));
		g2.draw(new Ellipse2D.Double(-(height/2),-(height/2),height,height));
		g2.setStroke(new BasicStroke(1));
		g2.draw(new Ellipse2D.Double(-(height/2)+5,-(height/2)+5,height-10,height-10));
		g2.setStroke(new BasicStroke(3));
		g2.draw(new Line2D.Double(0, 0, secArray[0][time.getSecond()],secArray[1][time.getSecond()] ));
		g2.setStroke(new BasicStroke(4));
		g2.draw(new Line2D.Double(0, 0, minArray[0][time.getMinute()],minArray[1][time.getMinute()] ));
		g2.setStroke(new BasicStroke(6));
		g2.draw(new Line2D.Double(0, 0, hourArray[0][time.getHour()*5],hourArray[1][time.getHour()*5] ));
		String currDate = time.getDayOfWeek() + " " + time.getMonth() + "/" + time.getDay() + "/" + time.getYear();
		g2.setFont(dateFont);
		g2.drawString(currDate,-width/2 + 10,-height/2 + 30);
	}
	
}
