import java.awt.Graphics;
import javax.swing.JPanel;
//View
abstract class DigitalClockView extends JPanel {
	private TheTime time;
	public TheTime getTicker(){
		return time;
	}
	public void draw() {
		repaint();
	}
	public abstract void paintComponent(Graphics g);
}